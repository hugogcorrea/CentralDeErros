package com.example.centralDeErros;

import com.v1.CentralDeErros.exceptions.EmptyListException;
import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.models.DTOs.UserDTO;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.repositories.ErrorRepository;
import com.v1.CentralDeErros.repositories.ServerRepository;
import com.v1.CentralDeErros.repositories.UserRepository;
import com.v1.CentralDeErros.services.ErrorService;
import com.v1.CentralDeErros.services.ServerService;
import com.v1.CentralDeErros.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
@SpringBootConfiguration
@SpringBootTest
class CentralDeErrosApplicationTests {

	@Mock
	UserApplication userApplication;

	@Mock
	ErrorRepository errorRepository;

	@Mock
	ServerRepository serverRepository;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	ErrorService errorService;

	@InjectMocks
	ServerService serverService;

	@InjectMocks
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test()
	public void shouldReturnAnEmptyListException(){
		Assertions.assertThrows(EmptyListException.class,() -> errorService.findAll(2));
	}

	@Test()
	public void shouldReturnNotFoundException(){
		Assertions.assertThrows(NotFoundException.class,() -> serverService.findAll(2));
	}

	@Test()
	public void shouldReturnUsernameNotFoundException(){
		Assertions.assertThrows(UsernameNotFoundException.class,() -> userService.findUser("Teste"));
	}

	@Test()
	public void shouldReturnNullPointerException(){
		UserDTO userDTO = new UserDTO();
		Assertions.assertThrows(NullPointerException.class,() -> userService.saveUser(userDTO));
	}

}
