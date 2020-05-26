package com.v1.CentralDeErros.services;

import com.v1.CentralDeErros.models.DTOs.ServerDTO;
import com.v1.CentralDeErros.models.Server;

import java.util.List;

public interface ServerServiceInterface {

    public void addNew(ServerDTO serverDTO);

    public List<Server> findAll(Integer size);
}
