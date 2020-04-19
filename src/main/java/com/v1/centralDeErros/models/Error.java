package com.v1.centralDeErros.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "transportadora")
public class Error {
	@Id
	@SequenceGenerator(name = "_id", sequenceName = "_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_id")
	private Integer _id;
	
	private String description;
	
	
}