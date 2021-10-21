package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CommandeClientDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "commandeClients/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto save(CommandeClientDto commandeClientDto);

    @GetMapping(value = APP_ROOT + "commandeClients/{idClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "commandeClients/{codeClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeClientDto findByCode(String code);

    @GetMapping(value = APP_ROOT + "commandeClients/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "commandeCleints/delete/{idCommandeClient}" , produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(Integer id);

}
