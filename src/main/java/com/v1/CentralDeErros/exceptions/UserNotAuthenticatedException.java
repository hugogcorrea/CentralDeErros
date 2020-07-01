package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class UserNotAuthenticatedException extends RuntimeException {

    private final String message;

    public UserNotAuthenticatedException(String message) {
        this.message = message;
    }
}
