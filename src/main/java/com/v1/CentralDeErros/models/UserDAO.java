package com.v1.CentralDeErros.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(name = "user")
public class UserDAO {

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