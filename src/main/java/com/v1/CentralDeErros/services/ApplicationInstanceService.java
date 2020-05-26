package com.v1.CentralDeErros.services;

import com.v1.CentralDeErros.models.Server;
import com.v1.CentralDeErros.repositories.ServerRepository;
import com.v1.CentralDeErros.util.DateUtility;
import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.exceptions.WrongInputDataException;
import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.DTOs.ApplicationInstanceDTO;
import com.v1.CentralDeErros.models.Error;
import com.v1.CentralDeErros.repositories.ApplicationInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApplicationInstanceService implements ApplicationInstanceServiceInterface {

    private final ApplicationInstanceRepository applicationInstanceRepository;
    private final ServerRepository serverRepository;

    @Autowired
    ApplicationInstanceService(ApplicationInstanceRepository applicationInstanceRepository,
                               ServerRepository serverRepository) {
        this.applicationInstanceRepository = applicationInstanceRepository;
        this.serverRepository = serverRepository;
    }

    // Aqui acontece o mapeamento de uma applicationDTO para uma Entity ApplicationInstance.
    @Override
    public void addNew(ApplicationInstanceDTO applicationInstanceDTO, Integer serverId) {
        Date instantiationDate;

        try {
            instantiationDate = DateUtility.getStringAsDate(applicationInstanceDTO.getInstantiationDate());
        } catch (ParseException ex) {
            throw new WrongInputDataException("A data enviada para esta aplicação não está em um formato válido." +
                    " Por favor, tente dd-MM-yyyy HH:mm:ss para a formatação");
        }

        Optional<Server> relatedServer = serverRepository.findById(serverId);

        if (!relatedServer.isPresent()) {
            throw new NotFoundException("Não existe um servidor com o id fornecido");
        }

        ApplicationInstance newApplicationInstance = new ApplicationInstance(applicationInstanceDTO.getApplicationName(),
                instantiationDate, relatedServer.get());

        applicationInstanceRepository.save(newApplicationInstance);
    }

    @Override
    public List<ApplicationInstance> findAll(Integer size) {
        List<ApplicationInstance> returnList = applicationInstanceRepository.findAll();

        if (returnList.isEmpty()) {
            throw new NotFoundException("Não há instâncias de aplicações no banco de dados");
        }

        return returnList.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public List<Error> errors(Integer id) {
        Optional<ApplicationInstance> returnApplication = applicationInstanceRepository.findById(id);

        if (!returnApplication.isPresent()) {
            throw new NotFoundException("Não existe uma aplicação com o id informado");
        }

        if (returnApplication.get().getError().isEmpty()) {
            throw new NotFoundException("Não existem erros associados a essa aplicação");
        }

        return returnApplication.get().getError();
    }
}
