package com.v1.centralDeErros.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.v1.centralDeErros.models.Error;
import com.v1.centralDeErros.services.IErrorService;

@Controller
public class ErrorController {


	@Autowired
	IErrorService errorService;

	@GetMapping("/errors")
	@ResponseBody
	public List<Error> listar() {
		return errorService.findAll();
	}
}
