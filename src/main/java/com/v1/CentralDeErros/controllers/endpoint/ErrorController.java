package com.v1.CentralDeErros.controllers.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.services.ErrorServiceInterface;

import io.swagger.annotations.ApiOperation;


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
