package com.v1.CentralDeErros.controllers;

import java.util.List;

import com.v1.CentralDeErros.exceptions.EmptyErrorListException;
import com.v1.CentralDeErros.exceptions.ErrorNotFoundException;
import com.v1.CentralDeErros.models.DTOs.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.services.ErrorServiceInterface;


@RestController
@RequestMapping("api/v1/errors")
public class ErrorController {

    private final ErrorServiceInterface errorService;

    @Autowired
    public ErrorController(ErrorServiceInterface errorService) {
        this.errorService = errorService;
    }

    // TODO: Checar documentação orElse()
    @GetMapping
    public List<Error> findAllErrors() {
        List<Error> allErrors = errorService.findAll();

        if (allErrors.isEmpty()) {
            throw new EmptyErrorListException();
        }

        return allErrors;
    }

    @GetMapping("/{id}")
    public Error findErrorById(@PathVariable Integer id) {
        return errorService.findById(id)
                .orElseThrow(ErrorNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    public void deleteErrorById(@PathVariable Integer id) {
        errorService.findById(id)
				.orElseThrow(ErrorNotFoundException::new);

        errorService.deleteById(id);
    }

}
