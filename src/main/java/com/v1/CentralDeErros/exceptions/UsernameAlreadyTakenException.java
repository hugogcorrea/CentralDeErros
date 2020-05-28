package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class UsernameAlreadyTakenException extends RuntimeException {

    private final String message;

    public UsernameAlreadyTakenException(String message) {
        this.message = message;
    }
}
