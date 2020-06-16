package com.v1.CentralDeErros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    static final String[] ROUTES = {"/authenticate", "/register", "/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**"};

    private final JwtRequestFilter jwtRequestFilter;
    private final PermissionFilter permissionFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Autowired
    public WebSecurityConfig(JwtRequestFilter jwtRequestFilter,
                             PermissionFilter permissionFilter,
                             CustomAccessDeniedHandler customAccessDeniedHandler,
                             CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                             ExceptionHandlerFilter exceptionHandlerFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.permissionFilter = permissionFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sem sessão

                .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(ROUTES).permitAll() // Não cheque nenhuma das requisições de ROUTES
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

}
