package com.v1.CentralDeErros.controllers;

import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.DTOs.ApplicationInstanceDTO;
import com.v1.CentralDeErros.models.DTOs.ErrorDTO;
import com.v1.CentralDeErros.services.ApplicationInstanceServiceInterface;
import com.v1.CentralDeErros.services.ErrorServiceInterface;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addNewApplication(@RequestBody ApplicationInstanceDTO applicationInstanceDTO) {

        applicationService.addNew(applicationInstanceDTO);
    }

    @GetMapping
    public List<ApplicationInstance> getAllApplications(){
        return applicationService.findAll();
    }


    @PostMapping("/{id}/error-submission")
    public void addNewError(@PathVariable Integer id, @RequestBody ErrorDTO errorDTO) {
        errorService.addNew(errorDTO, id);
    }

}
