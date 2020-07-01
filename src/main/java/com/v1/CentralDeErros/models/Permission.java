package com.v1.CentralDeErros.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Data
public class Permission {

	@EmbeddedId
	private PermissionId id;

	@CreatedDate
	private LocalDateTime createdAt;
}