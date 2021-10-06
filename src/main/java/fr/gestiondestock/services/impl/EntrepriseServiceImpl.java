package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.EntrepriseDto;
import fr.gestiondestock.exception.EntityNotFoundException;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.Entreprise;
import fr.gestiondestock.repository.EntrepriseRepository;
import fr.gestiondestock.services.EntrepriseService;
import fr.gestiondestock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        List<String> errors = EntrepriseValidator.validate(entrepriseDto);

        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}",entrepriseDto);
            throw new EntityNotValidException( "L'entreprise n'est pas valide" , ErrorCodes.ENTREPRISE_NOT_VALID , errors);
        }

        Entreprise entrepriseSaved = entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto));
        return EntrepriseDto.fromEntity(entrepriseSaved);

    }

    @Override
    public EntrepriseDto findById(Integer id) {

        if (id == null) {
            log.error("Entreprise ID is null");
            return null;
        }

        Optional<Entreprise> entrepriseDto =  entrepriseRepository.findById(id);
        return EntrepriseDto.fromEntity(
                entrepriseDto.orElseThrow(() -> {
                    log.error("Inexistant entreprise for id {}",id);
                    throw new EntityNotFoundException(String.format("Aucune entreprise avec l'ID %s n'a ete trouvee dans la BDD",id) ,ErrorCodes.ENTREPRISE_NOT_FOUND);
                })
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll()
                                   .stream()
                                   .map(EntrepriseDto::fromEntity)
                                   .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }
    }
}
