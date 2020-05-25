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


@Data
@NoArgsConstructor
@Entity
public class ApplicationInstance {

    @Id
    @SequenceGenerator(name = "id", sequenceName = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Date instantiationDate;

    private final ApplicationStatus status = ApplicationStatus.ACTIVE;

    @OneToMany(mappedBy = "applicationInstance",
            cascade = CascadeType.PERSIST
    )
    @JsonIgnore
    private final List<Error> error = new ArrayList<>();
    	
	@OneToMany(mappedBy = "id.applicationInstance")
	private List<Permission> permissions;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    @JsonIgnore
    private Server server;

    public ApplicationInstance(String applicationName, Date instantiationDate, Server server) {
        this.name = applicationName;
        this.instantiationDate = instantiationDate;
        this.server = server;
    }
}
