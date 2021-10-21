package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "commandeFournisseurs/create" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);

    @GetMapping(value = APP_ROOT + "commandeFournisseurs/{idCommandeFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(value = APP_ROOT + "commandeFournisseurs/{codeCommandeFournisseur}" , produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = APP_ROOT + "commandeFournisseurs//all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "commandeFournisseurs/delete/{idCommandeFournisseur}")
    void deleteById(@PathVariable("idCommandeFournisseur") Integer id);

}
