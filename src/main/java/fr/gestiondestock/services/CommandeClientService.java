package fr.gestiondestock.services;

import fr.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCodeCommande(String codeCommande);

    List<CommandeClientDto> findAll();

    void deleteById(Integer id);

}
