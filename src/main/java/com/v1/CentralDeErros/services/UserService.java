package com.v1.CentralDeErros.services;


import java.util.List;
import java.util.Optional;

import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.exceptions.UsernameAlreadyTakenException;
import com.v1.CentralDeErros.models.DTOs.UserDTO;
import com.v1.CentralDeErros.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.v1.CentralDeErros.exceptions.PasswordException;
import com.v1.CentralDeErros.exceptions.UserNotAuthenticatedException;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.repositories.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserApplication findUser(String username) {
        Optional<UserApplication> returnUser = userRepository.findByUsername(username);

        if (!returnUser.isPresent()) {
            throw new UsernameNotFoundException("Usuário " + username + " não existe");
        }

        return returnUser.get();
    }


    public void saveUser(UserDTO userDTO) {
        Optional<UserApplication> userWithTheSameUsername = userRepository.findByUsername(userDTO.getUsername());

        if (userWithTheSameUsername.isPresent()) {
            throw new UsernameAlreadyTakenException("Já existe um usuário com este nome.");
        }

        String newUserEncryptedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());

        UserApplication newUser = new UserApplication(userDTO.getUsername(), newUserEncryptedPassword);

        userRepository.save(newUser);
    }

    public void verifyUser(UserDTO user) {
        UserApplication userApp = findUser(user.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(user.getPassword(), userApp.getPassword())) {
            throw new PasswordException("Cadastro incorreto. Verifique sua senha.");
        }

    }

    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new AuthenticationCredentialsNotFoundException("Autentique-se novamente");
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new UserNotAuthenticatedException("Usuário não autenticado.");
        } else {
            return authentication.getName();
        }
    }

    public List<Permission> permissions(String username) {
        UserApplication user = findUser(username);

        if (user.getPermissions().isEmpty()) {
            throw new NotFoundException("Não existem permissões associadas a esse usuário.");
        }

        return user.getPermissions();
    }
}
