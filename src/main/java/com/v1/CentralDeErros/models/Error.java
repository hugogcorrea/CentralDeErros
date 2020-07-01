package com.v1.CentralDeErros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.v1.CentralDeErros.enums.ErrorType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Error {

    @Id
    @SequenceGenerator(name = "id", sequenceName = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Integer id;

    @NonNull
    private String description;

    @NonNull
    private Date registrationDate;

    // TODO: Pedir o tipo de erro via requestParam
    private ErrorType errorType = ErrorType.NON_CRASHING;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_instance_id")
    @JsonIgnore
    private ApplicationInstance applicationInstance;

    public Error(String description, Date registrationDate, ApplicationInstance applicationInstance) {
        this.description = description;
        this.registrationDate = registrationDate;
        this.applicationInstance = applicationInstance;
    }
}