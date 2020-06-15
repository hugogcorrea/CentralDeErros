package com.v1.CentralDeErros.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class UserApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @OneToMany(mappedBy = "id.userApplication",
            fetch = FetchType.EAGER)
    private List<Permission> permissions;

    public UserApplication(String username, String password) {
        this.username = username;
        this.password = password;
    }
}