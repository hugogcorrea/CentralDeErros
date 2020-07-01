package com.v1.CentralDeErros.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SystemUser extends User {

	private String name;

	public SystemUser(String name, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);

		this.name = name;
	}

	public String getName() {
		return name;
	}
}