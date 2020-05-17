package com.v1.CentralDeErros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.v1.CentralDeErros.models.JwtResponse;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.services.UserService;
import com.v1.CentralDeErros.services.authentication.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "JwtAuthentication")
public class LoginController {

	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private LoginService loginService;

	@Autowired
	public LoginController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
			LoginService loginService) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.loginService = loginService;
	}

	@ApiOperation(value = "Autenticar")
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserApplication user) throws Exception {
		final String token = loginService.addAuthentication(user);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@ApiOperation(value = "Registrar")
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserApplication user) throws Exception {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userService.saveUser(user);

		return new ResponseEntity<>("Cadastro realizado com sucesso!", HttpStatus.OK);
	}

	@ApiOperation(value = "Teste retorna user autenticado")
	@GetMapping("/teste")
	@ResponseBody
	public String teste() {
		return userService.getCurrentUserName();

	}

}