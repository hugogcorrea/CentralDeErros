package com.v1.CentralDeErros.models;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
@Data
public class PermissionId implements Serializable {

	@ManyToOne
	private ApplicationInstance applicationInstance;

	@ManyToOne
	private UserApplication userApplication;
}