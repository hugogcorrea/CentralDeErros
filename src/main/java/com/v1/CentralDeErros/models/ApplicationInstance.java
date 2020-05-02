package com.v1.CentralDeErros.models;

import com.v1.CentralDeErros.enums.ApplicationStatus;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "application_instance")
public class ApplicationInstance {
    @Id
    @SequenceGenerator(name = "id", sequenceName = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Integer id;

    @NonNull
    private String applicationName;

    private final LocalDate instantiationDate;
    // O valor default do status da aplicação é ativo. Todavia, ela pode tornar-se inativa à mando de uma requisição.
    private ApplicationStatus status = ApplicationStatus.ACTIVE;

    /* Cada instância de aplicação pode possuir muitos erros à ela associados. O único tipo de operação em cascata que
    * ela oferece suporte é o de persistência - isto é, se persistimos a applicationInstance em algum ponto, todos os
    * erros à ela associados automaticamente persistirão. Apesar do valor default ser false, é pertinente explicitar:
    * não há remoções de orfão. Um erro pode existir sem uma aplicação associada.*/
    @OneToMany(
            mappedBy = "applicationInstance",
            cascade = CascadeType.PERSIST
    )
    private List<Error> errors = new ArrayList<>();
}
