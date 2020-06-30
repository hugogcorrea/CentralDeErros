package com.example.centralDeErros;

import com.v1.CentralDeErros.exceptions.EmptyListException;
import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.repositories.ErrorRepository;
import com.v1.CentralDeErros.repositories.ServerRepository;
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
	ServerRepository serverRepository;

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

}
