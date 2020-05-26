package com.v1.CentralDeErros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.v1.CentralDeErros.enums.ApplicationStatus;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    @JsonIgnore
    private Server server;

    @OneToMany(mappedBy = "id.applicationInstance")
	private List<Permission> permissions;
    
    public ApplicationInstance(String applicationName, Date instantiationDate, Server server) {
        this.name = applicationName;
        this.instantiationDate = instantiationDate;
        this.server = server;
    }
}
