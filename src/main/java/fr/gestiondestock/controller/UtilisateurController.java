package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.UtilisateurApi;
import fr.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import fr.gestiondestock.dto.UtilisateurDto;
import fr.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<UtilisateurDto> save(UtilisateurDto utilisateurDto) {
        return ResponseEntity.ok(utilisateurService.save(utilisateurDto));
    }

    @Override
    public ResponseEntity<UtilisateurDto> findById(Integer id) {
        return ResponseEntity.ok(utilisateurService.findById(id));
    }

    @Override
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UtilisateurDto> changerMotDePasse(ChangerMotDePasseUtilisateurDto changerMotDePasseUtilisateurDto) {
        return ResponseEntity.ok(utilisateurService.changerMotDePasse(changerMotDePasseUtilisateurDto));
    }
}
