package fr.gestiondestock.controller.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import static fr.gestiondestock.utils.Constants.PHOTO_ENDPOINT;

@Api(value = PHOTO_ENDPOINT)
public interface PhotoApi {

    @PostMapping(value = PHOTO_ENDPOINT + "/save/{id}/{title}/{context}")
    Object savePhoto(@PathVariable("context") String context,
                     @PathVariable("id") Integer id,
                     @RequestPart("file") MultipartFile photo,
                     @PathVariable("title") String titre);

    }
