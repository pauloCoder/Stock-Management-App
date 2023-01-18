package fr.gestiondestock.services.strategy.impl;

import com.flickr4java.flickr.FlickrException;
import fr.gestiondestock.dto.ClientDto;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.exception.InvalidOperationException;
import fr.gestiondestock.services.ClientService;
import fr.gestiondestock.services.FlickrService;
import fr.gestiondestock.services.strategy.Strategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service(value = "clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto> {

    private final ClientService clientService;
    private final FlickrService flickrService;

    @Autowired
    public SaveClientPhoto(ClientService clientService, FlickrService flickrService) {
        this.clientService = clientService;
        this.flickrService = flickrService;
    }

    @Override
    public ClientDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        ClientDto clientDto = clientService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo du client", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        clientDto.setPhoto(urlPhoto);
        return clientService.save(clientDto);
    }
}
