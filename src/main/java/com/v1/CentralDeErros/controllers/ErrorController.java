package com.v1.CentralDeErros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.services.IErrorService;

@RestController
public class ErrorController {

	@Autowired
	IErrorService errorService;

	@GetMapping("/errors")
	@ResponseBody
	public List<Error> listar() {
		return errorService.findAll();
	}

	@GetMapping("/teste")
	@ResponseBody
	public String teste(Authentication aut) {
		return aut.getName();
	}
}
