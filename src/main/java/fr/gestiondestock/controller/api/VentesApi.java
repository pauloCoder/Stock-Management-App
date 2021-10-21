package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.VentesDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static fr.gestiondestock.utils.Constants.APP_ROOT;

public interface VentesApi {

    @GetMapping(value = APP_ROOT + "ventes/create" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto save(VentesDto ventesDto);

    @GetMapping(value = APP_ROOT + "ventes/{idVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "ventes/{codeVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(value = APP_ROOT + "ventes/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    List<VentesDto> findAll();

    @DeleteMapping(value = APP_ROOT + "ventes/{idVente}" , produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathVariable("idVente") Integer id);

}
