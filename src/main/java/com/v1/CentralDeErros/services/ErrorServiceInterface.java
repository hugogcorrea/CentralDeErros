package com.v1.CentralDeErros.services;

import java.util.List;
import java.util.Optional;

import com.v1.CentralDeErros.models.DTOs.ErrorDTO;
import com.v1.CentralDeErros.models.Error;

public interface ErrorServiceInterface {

	public List<Error> findAll(Integer size);

	public void addNew(ErrorDTO errorDTO, Integer applicationId);

	public Error findById(Integer id);

	public void deleteById(Integer id);
	
}
