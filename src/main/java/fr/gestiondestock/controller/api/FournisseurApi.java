package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "fournisseurs/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto save(FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "fournisseurs/{idFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "fournisseurs/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "fournisseurs/delete/{idFournisseur}")
    void deleteById(@PathVariable("idFournisseur") Integer id);
}
