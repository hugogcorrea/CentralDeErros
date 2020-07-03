package com.v1.CentralDeErros.config;

import com.v1.CentralDeErros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    static final String[] ROUTES = {"/authenticate", "/register", "/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**", "/roles"};

    static final String[] ROUTES_ADMIN = { "/**", "/api/v1/applications" };

	static final String[] ROUTES_USER = { "/api/v1/applications", "/api/v1/applications/**" };
	
    private final JwtRequestFilter jwtRequestFilter;
    private final PermissionFilter permissionFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final UserService userService;

    @Autowired
    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter,
                             PermissionFilter permissionFilter,
                             CustomAccessDeniedHandler customAccessDeniedHandler,
                             CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                             ExceptionHandlerFilter exceptionHandlerFilter,
                             UserService userService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.permissionFilter = permissionFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sem sessão

				.and()
				.csrf().disable()
				.authorizeRequests()
				  .antMatchers(ROUTES).permitAll()
				  .antMatchers(ROUTES_USER).hasRole("USER")
				  .antMatchers(ROUTES_ADMIN).hasRole("ADMIN")				 
				  .anyRequest().authenticated() // Qualquer outra requisição deve ser checada

                .and()
                    .exceptionHandling()
                    .accessDeniedHandler(customAccessDeniedHandler)

                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        /* Como a questão da verificação das permissões ocorre com usuário já autenticado, adicionamos o filtro depois
         * da autenticação realizada.*/
        httpSecurity.addFilterAfter(permissionFilter, UsernamePasswordAuthenticationFilter.class);

        /* ExceptionHandlerFilter é um subtipo de ExceptionTranslationFilter. É necessário fazer seu setup antes de
         * adicioná-lo na cadeia de filtros.*/
        exceptionHandlerFilter.setAccessDeniedHandler(customAccessDeniedHandler);

        /* Ele é adicionado antes de PermissionFilter pois ele tratará das exceções lançadas por esse filtro. Mais
         * especificamente exceções ligadas aos erros 403 (Forbidden) e 401 (Unauthorized).*/
        httpSecurity.addFilterBefore(exceptionHandlerFilter, PermissionFilter.class);
    }
    
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
