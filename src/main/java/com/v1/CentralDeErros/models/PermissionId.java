package com.v1.CentralDeErros.models;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class PermissionId implements Serializable {

	@ManyToOne
	private ApplicationInstance applicationInstance;

	@ManyToOne
	private UserApplication userApplication;
}