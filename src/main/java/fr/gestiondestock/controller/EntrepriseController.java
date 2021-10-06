package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.EntrepriseApi;
import fr.gestiondestock.dto.EntrepriseDto;
import fr.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        return entrepriseService.save(entrepriseDto);
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseService.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        entrepriseService.deleteById(id);
    }
}
