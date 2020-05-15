package com.v1.CentralDeErros.repositories;

import com.v1.CentralDeErros.models.ApplicationInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationInstanceRepository extends JpaRepository<ApplicationInstance, Integer> {

}
