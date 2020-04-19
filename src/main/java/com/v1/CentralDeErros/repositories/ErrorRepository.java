package com.v1.CentralDeErros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.CentralDeErros.models.Error;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Integer> {

}
