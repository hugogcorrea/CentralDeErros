package com.v1.CentralDeErros.models;

import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Data
public class Permission {
	@EmbeddedId
	private PermissionId id;

	@CreatedDate
	private LocalDateTime createdAt;
}
