package com.v1.CentralDeErros.services;

import com.v1.CentralDeErros.models.DTOs.ApplicationInstanceDTO;

public interface ApplicationInstanceInterface {
    public void postNew(ApplicationInstanceDTO applicationInstanceDTO);
}
