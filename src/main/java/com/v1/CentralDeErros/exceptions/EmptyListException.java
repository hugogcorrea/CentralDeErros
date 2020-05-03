package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class EmptyListException extends RuntimeException {

    public EmptyListException(String message) {
        this.message = message;
    }

    private String message;
}
