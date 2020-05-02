package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class ErrorNotFoundException extends RuntimeException {

    public ErrorNotFoundException(String message) {
        this.message = message;
    }

    private String message;
}
