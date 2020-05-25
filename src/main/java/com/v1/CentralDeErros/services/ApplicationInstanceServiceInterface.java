package com.v1.CentralDeErros.services;

import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.DTOs.ApplicationInstanceDTO;
import com.v1.CentralDeErros.models.Error;

import java.util.List;

public interface ApplicationInstanceServiceInterface {

    public void addNew(ApplicationInstanceDTO applicationInstanceDTO, Integer serverId);

    public List<ApplicationInstance> findAll(Integer size);

    public List<Error> errors(Integer id);
}
