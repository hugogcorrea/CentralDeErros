package com.v1.CentralDeErros.controllers;

import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.services.ErrorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/errors")
public class ErrorController {

    private final ErrorServiceInterface errorService;

    @Autowired
    public ErrorController(ErrorServiceInterface errorService) {
        this.errorService = errorService;
    }

    @GetMapping
    public List<Error> findAllErrors(@RequestParam Integer size) {
        return errorService.findAll(size);
    }

    @GetMapping("/{id}")
    public Error findErrorById(@PathVariable Integer id) {
        return errorService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteErrorById(@PathVariable Integer id) {
        errorService.deleteById(id);

        return new ResponseEntity<>("Erro deletado com sucesso", HttpStatus.OK);
    }
    
   

}
