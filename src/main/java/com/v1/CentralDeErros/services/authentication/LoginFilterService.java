package com.v1.CentralDeErros.services.authentication;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1.CentralDeErros.models.UserApplication;

public class LoginFilterService extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public LoginFilterService(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// Tentativa de autenticação
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserApplication credentials = new ObjectMapper().readValue(request.getInputStream(), UserApplication.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),
					credentials.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	// caso a tentativa acima for verdadeiro, este método é invocado
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication auth) throws IOException, ServletException {

		// TODO ADD HEADER
		// TODO -> criar log de usuario
		UserApplication user = new UserApplication();
		user.setUsername(auth.getName());
		
	}
}
