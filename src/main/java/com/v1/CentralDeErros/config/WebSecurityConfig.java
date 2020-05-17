package com.v1.CentralDeErros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private JwtRequestFilter jwtRequestFilter;
	private final String[] ROUTES = { "/authenticate", "/register", "/v2/api-docs", "/configuration/ui",
			"/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**" };

	@Autowired
	public WebSecurityConfig(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf()
		      	.disable()
		      	.authorizeRequests() // Não cheque nenhuma das requisições
				.antMatchers(ROUTES)
				.permitAll()
				// Qualquer outra requisição deve ser checada
				.anyRequest().authenticated().and().exceptionHandling()
				// .authenticationEntryPoint(springSecurityAuthenticationEntryPoint) // Exceções tratadas aqui
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Sem sessão

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
