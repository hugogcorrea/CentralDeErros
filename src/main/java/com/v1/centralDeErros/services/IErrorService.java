package com.v1.centralDeErros.services;

import java.util.List;
import java.util.Optional;

import com.v1.centralDeErros.models.Error;

public interface IErrorService {

	public List<Error> findAll();

	public Optional<Error> findOne(Integer id);
	
}
