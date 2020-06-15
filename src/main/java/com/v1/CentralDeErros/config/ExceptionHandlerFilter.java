package com.v1.CentralDeErros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import org.springframework.stereotype.Component;


@Component
public class ExceptionHandlerFilter extends ExceptionTranslationFilter {

    @Autowired
    public ExceptionHandlerFilter(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        super(customAuthenticationEntryPoint);
    }
}
