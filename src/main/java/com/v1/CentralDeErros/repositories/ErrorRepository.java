package com.v1.CentralDeErros.repositories;

import com.v1.CentralDeErros.models.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Integer> {

}
