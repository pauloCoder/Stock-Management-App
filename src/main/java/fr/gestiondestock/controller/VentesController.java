package fr.gestiondestock.controller;

import fr.gestiondestock.controller.api.VentesApi;
import fr.gestiondestock.dto.VentesDto;
import fr.gestiondestock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {

    private VentesService ventesService;

    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public ResponseEntity<VentesDto> save(VentesDto ventesDto) {
        return ResponseEntity.ok(ventesService.save(ventesDto));
    }

    @Override
    public ResponseEntity<VentesDto> findById(Integer id) {
        return ResponseEntity.ok(ventesService.findById(id));
    }

    @Override
    public ResponseEntity<VentesDto> findByCode(String code) {
        return ResponseEntity.ok(ventesService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<VentesDto>> findAll() {
        return ResponseEntity.ok(ventesService.findAll());
    }

    @Override
    public ResponseEntity deleteById(Integer id) {
        ventesService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
