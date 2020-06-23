package com.v1.CentralDeErros.services;


import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.exceptions.PasswordException;
import com.v1.CentralDeErros.exceptions.UserNotAuthenticatedException;
import com.v1.CentralDeErros.exceptions.UsernameAlreadyTakenException;
import com.v1.CentralDeErros.models.Permission;
import com.v1.CentralDeErros.models.Role;
import com.v1.CentralDeErros.models.SystemUser;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.models.DTOs.UserDTO;
import com.v1.CentralDeErros.repositories.RoleRepository;
import com.v1.CentralDeErros.repositories.UserRepository;


@Component
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserApplication findUser(String username) {
        Optional<UserApplication> returnUser = userRepository.findByUsername(username);

        if (!returnUser.isPresent()) {
            throw new UsernameNotFoundException("Usuário " + username + " não existe");
        }

        return returnUser.get();
    }


    public void saveUser(UserDTO userDTO) {
        Optional<UserApplication> userWithTheSameUsername = userRepository.findByUsername(userDTO.getUsername());

        if (userWithTheSameUsername.isPresent()) {
            throw new UsernameAlreadyTakenException("Já existe um usuário com este nome.");
        }

        String newUserEncryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());

        UserApplication newUser = new UserApplication(userDTO.getUsername(), newUserEncryptedPassword);

        userRepository.save(newUser);
    }

    public void verifyUser(UserDTO user) {
        UserApplication userApp = findUser(user.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(user.getPassword(), userApp.getPassword())) {
            throw new PasswordException("Cadastro incorreto. Verifique sua senha.");
        }

    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AuthenticationCredentialsNotFoundException("Autentique-se novamente");
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new UserNotAuthenticatedException("Usuário não autenticado.");
        } else {
            return authentication.getName();
        }
    }

    public List<Permission> permissions(String username) {
        UserApplication user = findUser(username);

        if (user.getPermissions().isEmpty()) {
            throw new NotFoundException("Não existem permissões associadas a esse usuário.");
        }

        return user.getPermissions();
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApplication user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Usuário não existe", username)));

		return new SystemUser(user.getUsername(), user.getUsername(), user.getPassword(), authorities(user));
	}

	public Collection<? extends GrantedAuthority> authorities(UserApplication user) {
		List<Role> roles = roleRepository.findByUsers(user);
        return roles.stream()
        		.map(p -> new SimpleGrantedAuthority("ROLE_" + p.getName())).collect(toList());
	}
}
