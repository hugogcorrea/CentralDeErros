package com.v1.CentralDeErros.controllers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordException extends RuntimeException {
	private static String MESSAGE = "Senha incorreta";

	public PasswordException() {
		super(MESSAGE);
	}
}
