package com.v1.CentralDeErros.repositories;

import com.v1.CentralDeErros.models.Role;
import com.v1.CentralDeErros.models.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByUsers(UserApplication user);
}