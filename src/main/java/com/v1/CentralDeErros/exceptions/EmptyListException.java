package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class EmptyListException extends RuntimeException {

    private final String message;

    public EmptyListException(String message) {
        this.message = message;
    }
}
