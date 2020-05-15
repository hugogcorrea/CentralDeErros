package com.v1.CentralDeErros.controllers.endpoint;

import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.DTOs.ApplicationInstanceDTO;
import com.v1.CentralDeErros.models.DTOs.ErrorDTO;
import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.services.ApplicationInstanceServiceInterface;
import com.v1.CentralDeErros.services.ErrorServiceInterface;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RequestMapping("api/v1//applications")
@RestController
public class ApplicationInstanceController {

    private final ApplicationInstanceServiceInterface applicationService;
    private final ErrorServiceInterface errorService;

    @Autowired
    public ApplicationInstanceController(ApplicationInstanceServiceInterface applicationService, ErrorServiceInterface
            errorService) {
        this.applicationService = applicationService;
        this.errorService = errorService;
    }

    @PostMapping
    public ResponseEntity<Object> addNewApplication(@RequestBody ApplicationInstanceDTO applicationInstanceDTO) {
        applicationService.addNew(applicationInstanceDTO);

        return new ResponseEntity<>("Aplicação adicionada com sucesso", HttpStatus.OK);
    }

    @GetMapping
    public List<ApplicationInstance> getAllApplications(@RequestParam Integer size){
        return applicationService.findAll(size);
    }


    @PostMapping("/{id}/error-submission")
    public ResponseEntity<Object> addNewError(@PathVariable Integer id, @RequestBody ErrorDTO errorDTO) {
        errorService.addNew(errorDTO, id);

        return new ResponseEntity<>("Erro reportado com sucesso", HttpStatus.OK);
    }

    @GetMapping("/{id}/errors")
    public List<Error> listErrors(@PathVariable Integer id) {
        return applicationService.errors(id);
    }
}
