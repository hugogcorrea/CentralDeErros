package com.v1.CentralDeErros.services;

import com.v1.CentralDeErros.exceptions.NotFoundException;
import com.v1.CentralDeErros.models.DTOs.ServerDTO;
import com.v1.CentralDeErros.models.Server;
import com.v1.CentralDeErros.repositories.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServerService implements ServerServiceInterface {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public void addNew(ServerDTO serverDTO) {
        serverRepository.save(new Server(serverDTO.getServerName(), serverDTO.getServerLocation()));
    }

    @Override
    public List<Server> findAll(Integer size) {
        List<Server> returnList = serverRepository.findAll();

        if (returnList.isEmpty()) {
            throw new NotFoundException("Não há servidores no banco de dados");
        }

        return returnList.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

}
