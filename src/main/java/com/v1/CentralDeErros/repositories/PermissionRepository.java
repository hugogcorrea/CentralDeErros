package com.v1.CentralDeErros.repositories;

import com.v1.CentralDeErros.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
	
	List<Permission> findByIdApplicationInstanceId(Integer applicationInstanceId);
	
	List<Permission> findByIdUserApplicationId(Integer userApplicationId);

}