package com.example.centralDeErros;

import com.v1.CentralDeErros.exceptions.EmptyListException;
import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.exceptions.UsernameAlreadyTakenException;
import com.v1.CentralDeErros.models.DTOs.ServerDTO;
import com.v1.CentralDeErros.models.DTOs.UserDTO;
import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.models.Server;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.repositories.ErrorRepository;
import com.v1.CentralDeErros.repositories.ServerRepository;
import com.v1.CentralDeErros.repositories.UserRepository;
import com.v1.CentralDeErros.services.ErrorService;
import com.v1.CentralDeErros.services.ServerService;
import com.v1.CentralDeErros.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@SpringBootConfiguration
@SpringBootTest
class CentralDeErrosApplicationTests {

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

	@Test()
	public void shouldReturnAnEmptyListException(){
		Assertions.assertThrows(EmptyListException.class,() -> errorService.findAll(2));
	}

	@Test()
	public void shouldReturnOneError(){

		List<Error> errors =  new ArrayList<>();

		Error error1 = new Error();
		error1.setDescription("Error1");
		Error error2 = new Error();
		error2.setDescription("Error2");
		Error error3 = new Error();
		error3.setDescription("Error3");

		errors.add(error1);
		errors.add(error2);
		errors.add(error3);

		given(errorRepository.findAll()).willReturn(errors);

		Assertions.assertEquals(1, errorService.findAll(1).size());
	}

	@Test()
	public void shouldReturnNotFoundExceptionErrorById(){

		List<Error> errors =  new ArrayList<>();

		Error error1 = new Error();
		error1.setDescription("Error1");
		Error error2 = new Error();
		error2.setDescription("Error2");
		Error error3 = new Error();
		error3.setDescription("Error3");

		errors.add(error1);
		errors.add(error2);
		errors.add(error3);

		given(errorRepository.findAll()).willReturn(errors);

		Assertions.assertThrows(NotFoundException.class,() -> errorService.findById(2));
	}

	@Test()
	public void shouldReturnNotFoundException(){
		Assertions.assertThrows(NotFoundException.class,() -> serverService.findAll(2));
	}

	@Test()
	public void shouldReturnOneServer(){

		List<Server> servers =  new ArrayList<>();

		servers.add(new Server("Server001","Brasil"));
		servers.add(new Server("Server002","Brasil"));
		servers.add(new Server("Server003","Brasil"));

		given(serverRepository.findAll()).willReturn(servers);

		Assertions.assertEquals(1, serverService.findAll(1).size());
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
