package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class WrongInputDataException extends RuntimeException {

    public WrongInputDataException(String message) {
        this.message = message;
    }

    private String message;
}
