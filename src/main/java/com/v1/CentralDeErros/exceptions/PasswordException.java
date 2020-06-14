package com.v1.CentralDeErros.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class PasswordException extends RuntimeException {

    private final String message;

    public PasswordException(String message) {
        this.message = message;
    }
}
