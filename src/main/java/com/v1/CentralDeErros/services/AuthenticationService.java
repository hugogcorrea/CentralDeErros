package com.v1.CentralDeErros.services;

import java.util.ArrayList;

import com.v1.CentralDeErros.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.v1.CentralDeErros.models.UserApp;


@Service
public class AuthenticationService implements UserDetailsService {


    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    public AuthenticationService(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;

    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserApp user = userService.findUser(username); // findUser já lança a exceção de UsernameNotFound

        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public String authenticateUserAndGenerateToken(UserApp userApp) {
        String username = userApp.getUsername();
        String password = userApp.getPassword();

        UserDetails userDetails = loadUserByUsername(username);

        // Gerando a autenticação...
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // OBS: Quaisquer erros lançados serão lançados e tratados pela Classe SpringSecurityAuthenticationEntryPoint
        // Gerando o JWT token a partir do userDetails...
        return jwtTokenUtil.generateToken(userDetails);
    }
}