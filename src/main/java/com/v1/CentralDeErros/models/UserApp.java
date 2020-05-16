package com.v1.CentralDeErros.models;


import javax.persistence.*;

@Entity
public class UserApp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	//@Column
	private String username;
	/*
	 * @Column
	 * 
	 * @JsonIgnore
	 */
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}