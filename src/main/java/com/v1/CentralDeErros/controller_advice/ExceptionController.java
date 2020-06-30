package com.v1.CentralDeErros.controller_advice;

import com.v1.CentralDeErros.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    private Map<String, String> jsonErrorResponse(String exceptionMessage) {
        Map<String, String> mappedValues = new HashMap<>();

        mappedValues.put("erro", exceptionMessage);

        return mappedValues;
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> exception(NotFoundException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyListException.class)
    public ResponseEntity<?> exception(EmptyListException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = WrongInputDataException.class)
    public ResponseEntity<?> exception(WrongInputDataException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordException.class)
    public ResponseEntity<?> exception(PasswordException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<?> exception(UsernameNotFoundException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotAuthenticatedException.class)
    public ResponseEntity<?> exception(UserNotAuthenticatedException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()),
                HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(value = UsernameAlreadyTakenException.class)
    public ResponseEntity<?> exception(UsernameAlreadyTakenException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<?> exception(ExpiredJwtException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
