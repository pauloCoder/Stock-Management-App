package fr.gestiondestock.controller.api;

import fr.gestiondestock.dto.MvtStockDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static fr.gestiondestock.utils.Constants.MVTSTOCK_ENDPOINT;

@Api(value = MVTSTOCK_ENDPOINT)
public interface MvtStockApi {

    @GetMapping(value = MVTSTOCK_ENDPOINT + "/stockreel/id/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = MVTSTOCK_ENDPOINT + "/find/id/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MvtStockDto> mvtStockArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping(value = MVTSTOCK_ENDPOINT + "/entree", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStockDto entreeStockArticle(@RequestBody MvtStockDto mvtStockDto);

    @PostMapping(value = MVTSTOCK_ENDPOINT + "/sortie", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStockDto sortieStockArticle(@RequestBody MvtStockDto mvtStockDto);

    @PostMapping(value = MVTSTOCK_ENDPOINT + "/correction/positive", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStockDto correctionStockPositive(@RequestBody MvtStockDto mvtStockDto);

    @PostMapping(value = MVTSTOCK_ENDPOINT + "/correction/negative", produces = MediaType.APPLICATION_JSON_VALUE)
    MvtStockDto correctionStockNegative(@RequestBody MvtStockDto mvtStockDto);
}
