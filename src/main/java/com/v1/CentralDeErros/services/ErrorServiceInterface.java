package com.v1.CentralDeErros.services;

import java.util.List;
import java.util.Optional;

import com.v1.CentralDeErros.models.Error;

public interface IErrorService {

	public List<Error> findAll();

	public void postNew(Error error);

	public Optional<Error> findById(Integer id);

	public void deleteById(Integer id);
	
}
