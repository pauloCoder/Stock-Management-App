package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.FournisseurDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.model.CommandeFournisseur;
import fr.gestiondestock.model.Fournisseur;
import fr.gestiondestock.repository.CommandeFournisseurRepository;
import fr.gestiondestock.repository.FournisseurRepository;
import fr.gestiondestock.services.FournisseurService;
import fr.gestiondestock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    private final CommandeFournisseurRepository commandeFournisseurRepository;

    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository,
                                  CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {

        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Fournisseur is not valid {}",fournisseurDto);
            throw new EntityNotValidException( "Le fournisseur n'est pas valide" , ErrorCodes.FOURNISSEUR_NOT_VALID , errors);
        }

        Fournisseur fournisseurSaved = fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto));
        return  FournisseurDto.fromEntity(fournisseurSaved);

    }

    @Override
    public FournisseurDto findById(Integer id) {

        if (id == null) {
            log.error("Fournisseur ID is null");
            return null;
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        return FournisseurDto.fromEntity(
                fournisseur.orElseThrow( () -> {
                    log.error("Inexistant fournisseur for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucun fournisseur avec l'ID %s n'a ete trouvee dans la BDD",id) ,ErrorCodes.FOURNISSEUR_NOT_FOUND);
                })
        );

    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            log.error("Fournisseur ID is null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un fournisseur avec des commande fournisseur", ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);

    }

}
