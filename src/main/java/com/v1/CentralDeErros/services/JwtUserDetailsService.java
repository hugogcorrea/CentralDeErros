package com.v1.CentralDeErros.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.v1.CentralDeErros.models.UserDAO;
import com.v1.CentralDeErros.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDAO user = userRepo.findByUsername(username);

		if (user != null) {
			return new User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public UserDAO save(UserDAO user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
}