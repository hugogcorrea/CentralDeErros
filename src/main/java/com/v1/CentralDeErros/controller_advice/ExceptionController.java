package com.v1.CentralDeErros.controller_advice;

import com.v1.CentralDeErros.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> exception(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyListException.class)
    public ResponseEntity<Object> exception(EmptyListException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = WrongInputDataException.class)
    public ResponseEntity<Object> exception(WrongInputDataException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordException.class)
    public ResponseEntity<Object> exception(PasswordException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exception(UsernameNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserNotAuthenticatedException.class)
    public ResponseEntity<Object> exception(UserNotAuthenticatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(value = UsernameAlreadyTakenException.class)
    public ResponseEntity<Object> exception(UsernameAlreadyTakenException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
