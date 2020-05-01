package com.v1.CentralDeErros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ExemploException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static String MESSAGE = "Usuário não cadastrado";

	public ExemploException() {
		super(MESSAGE);
	}

}
