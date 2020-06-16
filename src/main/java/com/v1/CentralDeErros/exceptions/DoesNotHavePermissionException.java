package com.v1.CentralDeErros.exceptions;

import lombok.Getter;

@Getter
public class DoesNotHavePermissionException extends RuntimeException {

    private final String message;

    public DoesNotHavePermissionException(String message) {
        this.message = message;
    }
}
