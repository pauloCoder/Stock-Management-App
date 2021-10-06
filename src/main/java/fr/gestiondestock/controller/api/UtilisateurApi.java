package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "utilisateurs/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto save(UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "utilisateurs/{utilisateurId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    UtilisateurDto findById(@PathVariable("utilisateurId") Integer id);

    @GetMapping(value = APP_ROOT + "utilisateurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "utilisateurs/delete/{utilisateurId}")
    void deleteById(@PathVariable("utilisateurId") Integer id);

}
