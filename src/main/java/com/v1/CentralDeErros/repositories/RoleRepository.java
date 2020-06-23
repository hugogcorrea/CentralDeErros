package com.v1.CentralDeErros.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.CentralDeErros.models.Role;
import com.v1.CentralDeErros.models.UserApplication;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByUsers(UserApplication user);
}