package com.v1.CentralDeErros.repositories;

import com.v1.CentralDeErros.models.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApplication, Integer> {
	Optional<UserApplication> findByUsername(String userName);
}
