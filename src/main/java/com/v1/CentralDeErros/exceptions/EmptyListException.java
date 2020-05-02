package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class EmptyErrorListException extends RuntimeException {

    public EmptyErrorListException(String message) {
        this.message = message;
    }

    private String message;
}
