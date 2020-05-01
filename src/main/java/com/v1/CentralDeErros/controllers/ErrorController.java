package com.v1.CentralDeErros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.services.IErrorService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ErrorController {

	@Autowired
	IErrorService errorService;

	@ApiOperation(value = "Listar erros")
	@GetMapping("/errors")
	@ResponseBody
	public List<Error> listar() {
		return errorService.findAll();
	}
  
	@ApiOperation(value = "Teste retorna user autenticado")
	@GetMapping("/teste")
	@ResponseBody
	public Authentication teste(Authentication aut) {
		return aut;
	}
}
