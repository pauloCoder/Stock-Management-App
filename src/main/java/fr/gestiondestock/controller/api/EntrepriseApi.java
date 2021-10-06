package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.EntrepriseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "entreprises/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "entreprises/{entrepriseId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable("entrepriseId") Integer id);

    @GetMapping(value = APP_ROOT + "entreprises/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @GetMapping(value = APP_ROOT + "entreprises/{entrepriseId}")
    void deleteById(@PathVariable("entrepriseId") Integer id);

}
