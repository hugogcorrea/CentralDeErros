package com.v1.CentralDeErros.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.v1.CentralDeErros.enums.ApplicationStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "application_instance")
public class ApplicationInstance {

    @Id
    @SequenceGenerator(name = "id", sequenceName = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Integer id;

    @NonNull
    private String applicationName;

    @NonNull
    private Date instantiationDate;

    private ApplicationStatus status = ApplicationStatus.ACTIVE;

    @JsonIgnore
    @OneToMany(mappedBy = "applicationInstance",
            cascade = CascadeType.PERSIST
    )
    private List<Error> error = new ArrayList<>();
    	
	@OneToMany(mappedBy = "id.applicationInstance")
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
