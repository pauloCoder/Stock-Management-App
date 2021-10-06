package fr.gestiondestock.services;

import fr.gestiondestock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto findById(Integer id);

    List<ClientDto> findAll();

    void deleteById(Integer id);
}
