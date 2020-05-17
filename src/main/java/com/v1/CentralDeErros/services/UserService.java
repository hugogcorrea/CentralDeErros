package com.v1.CentralDeErros.services;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.v1.CentralDeErros.exceptions.PasswordException;
import com.v1.CentralDeErros.exceptions.UserNotAuthenticatedException;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserApplication findUser(String userName) {
		Optional<UserApplication> returnUser = userRepository.findByUserName(userName);

		if (!returnUser.isPresent()) {
			throw new UsernameNotFoundException("Usuário " + userName + " não existe");
		}

		return returnUser.get();
	}

	// Checar aqui se já existe um usuário com esse nome ou não.
	public void saveUser(UserApplication userApp) {
		userRepository.save(userApp);
	}

	// OBS. ESTE METODO É USADO PARA LOGAR COM O USERNAME E BUSCAR O ID NO BANCO,
	// COMO NO NOSSO CASO O EMAIL(USERNAME) É O ID, NAO VEJO NECESSIDADE DO SEU
	// USO..
	// TEREMOS QUE AVALIAR MELHOR...
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserApplication userApp = userRepository.findByUserName(userName).get();
		if (userApp == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new User(userApp.getUsername(), userApp.getPassword(), emptyList());
	}

	public void verifyUser(UserApplication user) throws PasswordException {
		UserApplication userApp = findUser(user.getUsername());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (!passwordEncoder.matches(user.getPassword(), userApp.getPassword())) {
			throw new PasswordException();
		}
	}

	public String getCurrentUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		} else {
			throw new UserNotAuthenticatedException();
		}
	}

}
