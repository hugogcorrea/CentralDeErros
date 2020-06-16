package com.v1.CentralDeErros.controller_advice;

import com.v1.CentralDeErros.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private JSONObject jsonErrorResponse(String exceptionMessage) {
        return new JSONObject("{ \"erro\": \"" + exceptionMessage + "\" }");
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<JSONObject> exception(NotFoundException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyListException.class)
    public ResponseEntity<JSONObject> exception(EmptyListException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = WrongInputDataException.class)
    public ResponseEntity<JSONObject> exception(WrongInputDataException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordException.class)
    public ResponseEntity<JSONObject> exception(PasswordException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<JSONObject> exception(UsernameNotFoundException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotAuthenticatedException.class)
    public ResponseEntity<JSONObject> exception(UserNotAuthenticatedException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()),
                HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(value = UsernameAlreadyTakenException.class)
    public ResponseEntity<JSONObject> exception(UsernameAlreadyTakenException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<JSONObject> exception(ExpiredJwtException exception) {
        return new ResponseEntity<>(jsonErrorResponse(exception.getMessage()), HttpStatus.UNAUTHORIZED);
    }

}
