package com.v1.centralDeErros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.v1.centralDeErros.models.Error;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Integer> {

}
