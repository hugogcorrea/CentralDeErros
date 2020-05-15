package com.v1.CentralDeErros.controllers;

import com.v1.CentralDeErros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.v1.CentralDeErros.config.JwtTokenUtil;
import com.v1.CentralDeErros.models.JwtResponse;
import com.v1.CentralDeErros.models.UserApp;
import com.v1.CentralDeErros.services.AuthenticationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(value = "JwtAuthentication")
public class JwtAuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;

	@ApiOperation(value = "Autenticar")
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserApp userApp) throws Exception {
		final String token = authenticationService.authenticateUserAndGenerateToken(userApp);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@ApiOperation(value = "Registrar")
	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody UserApp user) throws Exception {
		userService.saveUser(user);

		return new ResponseEntity<>("Usuário cadastrado com sucesso!", HttpStatus.OK);
	}

}