package com.v1.CentralDeErros.repositories;

import com.v1.CentralDeErros.models.ApplicationInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationInstanceRepository extends JpaRepository<ApplicationInstance, Integer> {
}
