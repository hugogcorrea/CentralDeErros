package com.v1.CentralDeErros.config;

import com.v1.CentralDeErros.services.authentication.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	LoginService loginService;

	@Autowired
	public JwtRequestFilter(LoginService loginService) {
		this.loginService = loginService;
	}

	// Validamos a existência de um JWT nas requisições com ajuda do LoginService
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer")) {
			Authentication authentication = loginService.getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}
}