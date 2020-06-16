package com.v1.CentralDeErros.services.authentication;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.v1.CentralDeErros.models.DTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {

    static final long EXPIRATION_TIME = 999999999;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    private final UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginService(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String addAuthentication(UserDTO user) {
        /* Caso não exista um usuário com esse nome, uma UsernameNotFoundException será lançada.
         * Caso a senha esteja incorreta, será lançada uma PasswordException.
         * Ambas as exceções são tratadas no ExceptionController. */
        userService.verifyUser(user);

        String JWT = Jwts
                .builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        return JWT;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        UsernamePasswordAuthenticationToken returnAuthenticationToken = null;

        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // Faz parse do token.
            String user = Jwts
                    .parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                    .getSubject();

            if (user != null) {
                returnAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            }
        }

        return returnAuthenticationToken;
    }
}
