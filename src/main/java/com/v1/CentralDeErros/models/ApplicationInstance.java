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

}
