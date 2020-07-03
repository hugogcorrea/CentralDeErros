package com.v1.CentralDeErros.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<UserApplication> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserApplication> getUsers() {
		return users;
	}

	public void setUsers(List<UserApplication> users) {
		this.users = users;
	}
}