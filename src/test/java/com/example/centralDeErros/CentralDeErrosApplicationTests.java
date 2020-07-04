package com.example.centralDeErros;

import com.v1.CentralDeErros.exceptions.EmptyListException;
import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.UserApplication;
import com.v1.CentralDeErros.repositories.ApplicationInstanceRepository;
import com.v1.CentralDeErros.repositories.ErrorRepository;
import com.v1.CentralDeErros.repositories.ServerRepository;
import com.v1.CentralDeErros.repositories.UserRepository;
import com.v1.CentralDeErros.services.ErrorService;
import com.v1.CentralDeErros.services.ServerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootConfiguration
@SpringBootTest
class CentralDeErrosApplicationTests {

	@Mock
	ErrorRepository errorRepository;

	@Mock
	ApplicationInstanceRepository applicationInstanceRepository;

	@Mock
	ServerRepository serverRepository;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	ErrorService errorService;

	@InjectMocks
	ServerService serverService;

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

	@Test
	public void shouldCreateUser() {

		UserApplication userApplication = new UserApplication();
		userApplication.setUsername("teste");
		userApplication.setPassword("teste");

		UserApplication found = userRepository.save(userApplication);
		System.out.println(userApplication);
	}
	@Test
	public void shouldCreateApplication() {
		ApplicationInstance applicationInstance = new ApplicationInstance();
		applicationInstance.setName("Nuvem");
		applicationInstance.setId(12);
		ApplicationInstance found = applicationInstanceRepository.save(applicationInstance);
		System.out.println(applicationInstance);
	}

}
