package com.v1.CentralDeErros.config;

import com.v1.CentralDeErros.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SpringSecurityAuthenticationEntryPoint springSecurityAuthenticationEntryPoint;

	private UserDetailsService authenticationService;

	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Autowired // Setter injection para quebrar o ciclo...
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests() // Não cheque nenhuma das requisições
				.antMatchers("/authenticate", "/register", "/v2/api-docs", "/configuration/ui",
						"/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
				// Qualquer outra requisição deve ser checada
				.anyRequest().authenticated()
				.and().exceptionHandling().authenticationEntryPoint(springSecurityAuthenticationEntryPoint) // Exceções tratadas aqui
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Sem sessão

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

}