package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.CommandeClientDto;
import fr.gestiondestock.repository.CommandeClientRepository;
import fr.gestiondestock.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {
        return null;
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return null;
    }

    @Override
    public CommandeClientDto findByCodeCommande(String codeCommande) {
        return null;
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
