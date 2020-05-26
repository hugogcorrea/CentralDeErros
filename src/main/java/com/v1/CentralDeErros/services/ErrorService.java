package com.v1.CentralDeErros.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.v1.CentralDeErros.Util.DateUtility;
import com.v1.CentralDeErros.exceptions.EmptyListException;
import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.exceptions.WrongInputDataException;
import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.DTOs.ErrorDTO;
import com.v1.CentralDeErros.repositories.ApplicationInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.repositories.ErrorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ErrorService implements ErrorServiceInterface {

    private final ErrorRepository errorRepository;
    private final ApplicationInstanceRepository applicationInstanceRepository;

    @Autowired
    public ErrorService(ErrorRepository errorRepository, ApplicationInstanceRepository applicationInstanceRepository) {
        this.errorRepository = errorRepository;
        this.applicationInstanceRepository = applicationInstanceRepository;
    }

    @Override
    public List<Error> findAll(Integer size) {
        List<Error> returnList = errorRepository.findAll();

        if (returnList.isEmpty()) {
            throw new EmptyListException("Não há erros registrados no banco de dados");
        }

        return returnList.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    // Aqui acontece o mapeamento de um errorDTO para uma Entity Error.
    @Override
    public void addNew(ErrorDTO errorDTO, Integer applicationId) {
        Date registrationDate;

        try {
            registrationDate = DateUtility.getStringAsDate(errorDTO.getRegistrationDate());
        } catch (ParseException ex) {
            throw new WrongInputDataException("A data enviada para este erro não está em um formato válido." +
                    " Por favor, tente dd-MM-yyyy HH:mm:ss para a formatação");
        }

        Optional<ApplicationInstance> relatedApplicationInstance =
                applicationInstanceRepository.findById(applicationId);

        if (!relatedApplicationInstance.isPresent()) {
            throw new NotFoundException("Não existe uma instância de aplicação com o id fornecido");
        }

        Error newError = new Error(errorDTO.getDescription(), registrationDate, relatedApplicationInstance.get());

        errorRepository.save(newError);
    }

    @Override
    public Error findById(Integer id) {
        Optional<Error> returnError = errorRepository.findById(id);

        if (!returnError.isPresent()) {
            throw new NotFoundException("Não existe um erro com esse id");
        }

        return returnError.get();
    }

    @Override
    public void deleteById(Integer id) {
        if (!errorRepository.existsById(id)) {
            throw new NotFoundException("Não existe um erro com esse id");
        }

        errorRepository.deleteById(id);
    }

}
