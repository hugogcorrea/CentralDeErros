package com.v1.CentralDeErros.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Server {

    @Id
    @SequenceGenerator(name = "id", sequenceName = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String location;

    @OneToMany(mappedBy = "server",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private final List<ApplicationInstance> applicationInstances = new ArrayList<>();

    public Server(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
