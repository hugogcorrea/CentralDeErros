package com.v1.CentralDeErros.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.v1.CentralDeErros.enums.ErrorType;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "error")
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
    @JoinColumn(name = "application_instance_id", referencedColumnName = "id")
    @JsonBackReference
    private ApplicationInstance applicationInstance;
}