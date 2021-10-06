package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Client;
import fr.gestiondestock.repository.ClientRepository;
import fr.gestiondestock.services.ClientService;
import fr.gestiondestock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {

        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}",clientDto);
            throw new EntityNotValidException( "Le client n'est pas valide" , ErrorCodes.CLIENT_NOT_VALID , errors);
        }

        Client clientSaved = clientRepository.save(ClientDto.toEntity(clientDto));
        return  ClientDto.fromEntity(clientSaved);

    }

    @Override
    public ClientDto findById(Integer id) {

        if (id == null) {
            log.error("Client ID is null");
            return null;
        }

        Optional<Client> client = clientRepository.findById(id);
        return ClientDto.fromEntity(
                client.orElseThrow( () -> {
                    log.error("Inexistant client for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucun client avec l'ID %s n'a ete trouvee dans la BDD",id) ,ErrorCodes.CLIENT_NOT_FOUND);
                })
        );

    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                               .stream()
                               .map(ClientDto::fromEntity)
                               .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Client ID is null");
            return;
        }

        clientRepository.deleteById(id);

    }
}
