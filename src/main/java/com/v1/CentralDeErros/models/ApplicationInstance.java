package com.v1.CentralDeErros.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.v1.CentralDeErros.enums.ApplicationStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


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

    @OneToMany(mappedBy = "applicationInstance",
            cascade = CascadeType.PERSIST
    )
    @JsonManagedReference
    private List<Error> error = new ArrayList<>();

}
