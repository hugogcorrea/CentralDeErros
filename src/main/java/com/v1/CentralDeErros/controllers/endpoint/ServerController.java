package com.v1.CentralDeErros.controllers.endpoint;

import com.v1.CentralDeErros.models.ApplicationInstance;
import com.v1.CentralDeErros.models.DTOs.ApplicationInstanceDTO;
import com.v1.CentralDeErros.models.DTOs.ServerDTO;
import com.v1.CentralDeErros.models.Server;
import com.v1.CentralDeErros.services.ApplicationInstanceServiceInterface;
import com.v1.CentralDeErros.services.ServerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/servers")
@RestController
public class ServerController {

    private final ApplicationInstanceServiceInterface applicationService;
    private final ServerServiceInterface serverService;

    @Autowired
    public ServerController(ApplicationInstanceServiceInterface applicationService,
                            ServerServiceInterface serverService) {
        this.applicationService = applicationService;
        this.serverService = serverService;
    }

    @PostMapping("/{id}/application-creation")
    public ResponseEntity<Object> addNewApplication(@PathVariable Integer id,
                                                    @RequestBody ApplicationInstanceDTO applicationInstanceDTO) {
        applicationService.addNew(applicationInstanceDTO, id);

        return new ResponseEntity<>("Aplicação adicionada com sucesso", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addNewServer(@RequestBody ServerDTO serverDTO) {
        serverService.addNew(serverDTO);

        return new ResponseEntity<>("Servidor adicionado com sucesso", HttpStatus.OK);
    }

    @GetMapping
    public List<Server> getAllServers(@RequestParam Integer size) {
        return serverService.findAll(size);
    }
}
