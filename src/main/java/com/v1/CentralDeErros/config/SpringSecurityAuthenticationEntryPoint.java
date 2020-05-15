package com.v1.CentralDeErros.config;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// Esse componente, apesar de não ser um controlador, lidará com TODAS as exceções ligadas à autenticação.
@Component
public class SpringSecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());

	}
}