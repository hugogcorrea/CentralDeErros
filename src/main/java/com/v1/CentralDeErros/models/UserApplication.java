package com.v1.CentralDeErros.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    
    @ManyToMany
    private List<Role> roles;
    
    public UserApplication(String username, String password) {
        this.username = username;
        this.password = password;
    }
}