package com.v1.CentralDeErros.services;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.v1.CentralDeErros.models.UserApp;
import com.v1.CentralDeErros.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserApp findUser(String username) {
		Optional<UserApp> returnUser = userRepository.findByUsername(username);

		if (!returnUser.isPresent()) {
			throw new UsernameNotFoundException("Usuário " + username + " não existe");
		}

		return returnUser.get();
	}

	// Checar aqui se já existe um usuário com esse nome ou não.
	public void saveUser(UserApp userApp) {
		userRepository.save(userApp);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp userApp = userRepository.findByUsername(username).get();
		if (userApp == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(userApp.getUsername(), userApp.getPassword(), emptyList());
	}
}
