package fr.gestiondestock.services;

import fr.gestiondestock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void deleteById(Integer id);

}
