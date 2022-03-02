package fr.gestiondestock.services;

import fr.gestiondestock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Integer id);

    UtilisateurDto findByEmail(String email);

    List<UtilisateurDto> findAll();

    void deleteById(Integer id);

}
