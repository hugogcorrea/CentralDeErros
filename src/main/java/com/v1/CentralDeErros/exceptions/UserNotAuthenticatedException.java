package com.v1.CentralDeErros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotAuthenticatedException extends RuntimeException {
	private static String MESSAGE = "Usuario nao autenticado";

	public UserNotAuthenticatedException() {
		super(MESSAGE);
	}
}
