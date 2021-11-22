package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.ClientApi;
import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        return  ResponseEntity.ok(clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> findById(Integer id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @Override
    public ResponseEntity deleteById(Integer id) {
        clientService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
