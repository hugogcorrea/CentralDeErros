package com.v1.CentralDeErros.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.v1.CentralDeErros.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
	
	List<Permission> findByIdApplicationInstanceId(Integer applicationInstanceId);
	
	List<Permission> findByIdUserApplicationId(Integer userApplicationId);

}