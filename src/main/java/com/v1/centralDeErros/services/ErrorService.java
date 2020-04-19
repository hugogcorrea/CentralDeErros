package com.v1.centralDeErros.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.centralDeErros.models.Error;
import com.v1.centralDeErros.repositories.ErrorRepository;

@Service
public class ErrorService implements IErrorService{

	@Autowired
	ErrorRepository repository;
	
	@Override
	public List<Error> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Error> findOne(Integer id) {
		return repository.findById(id);
	}

}
