package fr.gestiondestock.services.impl;

import fr.gestiondestock.dto.MvtStockDto;
import fr.gestiondestock.exception.EntityNotValidException;
import fr.gestiondestock.exception.ErrorCodes;
import fr.gestiondestock.model.TypeMvtStock;
import fr.gestiondestock.repository.MvtStockRepository;
import fr.gestiondestock.services.ArticleService;
import fr.gestiondestock.services.MvtStockService;
import fr.gestiondestock.validator.MvtStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MvtStockServiceImpl implements MvtStockService {

    private final MvtStockRepository mvtStockRepository;
    private final ArticleService articleService;

    public MvtStockServiceImpl(MvtStockRepository mvtStockRepository, ArticleService articleService) {
        this.mvtStockRepository = mvtStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("ID article is null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStockRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStockDto> mvtStockArticle(Integer idArticle) {
        return mvtStockRepository.findAllByArticleId(idArticle)
                .stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStockDto entreeStockArticle(MvtStockDto mvtStockDto) {
        return entreeOuSortie(mvtStockDto, TypeMvtStock.ENTREE, true);
    }

    @Override
    public MvtStockDto sortieStockArticle(MvtStockDto mvtStockDto) {
        return entreeOuSortie(mvtStockDto, TypeMvtStock.SORTIE, false);
    }

    @Override
    public MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto) {
        return entreeOuSortie(mvtStockDto, TypeMvtStock.CORRECTION_POSITIVE, true);
    }

    @Override
    public MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto) {
        return entreeOuSortie(mvtStockDto, TypeMvtStock.CORRECTION_NEGATIVE, false);
    }

    private MvtStockDto entreeOuSortie(MvtStockDto mvtStockDto, TypeMvtStock typeMvtStock, boolean entree) {
        int mul = entree ?  1 : -1;
        List<String> errors = MvtStockValidator.validate(mvtStockDto);
        if (!errors.isEmpty()) {
            log.error("MvtStock is not valid {}", mvtStockDto);
            throw new EntityNotValidException("Le mouvement de stock n'est pas valide", ErrorCodes.MVT_STOCK_NOT_VALID, errors);
        }
        mvtStockDto.setQuantite(
                BigDecimal.valueOf(
                        mul * Math.abs(mvtStockDto.getQuantite().doubleValue())
                )
        );
        mvtStockDto.setTypeMvt(typeMvtStock);
        return MvtStockDto.fromEntity(
                mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto))
        );
    }

}
