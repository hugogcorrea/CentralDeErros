package com.v1.CentralDeErros.services;


import com.v1.CentralDeErros.models.UserApp;
import com.v1.CentralDeErros.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserApp findUser(String username) {
        Optional<UserApp> returnUser = userRepository.findByUsername(username);

        if (!returnUser.isPresent()) {
            throw new UsernameNotFoundException("Usuário " + username + " não existe");
        }

        return returnUser.get();
    }


    // Checar aqui se já existe um usuário com esse nome ou não.
    public void saveUser(UserApp userApp) {
        userApp.setPassword(bcryptEncoder.encode(userApp.getPassword()));

        userRepository.save(userApp);
    }
}
