package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.EntrepriseApi;
import fr.gestiondestock.dto.EntrepriseDto;
import fr.gestiondestock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public ResponseEntity<EntrepriseDto> save(EntrepriseDto entrepriseDto) {
        return ResponseEntity.ok(entrepriseService.save(entrepriseDto));
    }

    @Override
    public ResponseEntity<EntrepriseDto> findById(Integer id) {
        return ResponseEntity.ok(entrepriseService.findById(id));
    }

    @Override
    public ResponseEntity<List<EntrepriseDto>> findAll() {
        return ResponseEntity.ok(entrepriseService.findAll());
    }

    @Override
    public ResponseEntity deleteById(Integer id) {
        entrepriseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
