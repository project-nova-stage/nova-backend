package com.nova.backend.mapper.catalogo;

import com.nova.backend.dto.catalogo.ImmagineProdottoDTO;
import com.nova.backend.dto.catalogo.ProdottoRequest;
import com.nova.backend.dto.catalogo.ProdottoResponse;
import com.nova.backend.dto.catalogo.SpecificaProdottoDTO;
import com.nova.backend.model.catalogo.Categoria;
import com.nova.backend.model.catalogo.ImmagineProdotto;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.catalogo.SpecificaProdotto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdottoMapper {

    private final CategoriaMapper categoriaMapper;

    public ProdottoMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    // Da DTO a Entity (Salvataggio nel DB)
    public Prodotto toEntity(ProdottoRequest request) {
        Prodotto prodotto = new Prodotto();
        prodotto.setSku(request.sku());
        prodotto.setNome(request.name());
        prodotto.setPrezzo(request.price());
        prodotto.setQuantitaDisponibile(request.stockQuantity());
        prodotto.setAttivo(request.isActive() != null ? request.isActive() : true);

        // Associazione Categoria
        if (request.categoryId() != null) {
            Categoria categoria = new Categoria();
            categoria.setId(request.categoryId());
            prodotto.setCategoria(categoria);
        }

        // Associazione Immagini (fondamentale settare il riferimento al prodotto padre)
        if (request.images() != null) {
            List<ImmagineProdotto> immagini = request.images().stream().map(dto -> {
                ImmagineProdotto img = new ImmagineProdotto();
                img.setUrlImmagine(dto.imageUrl());
                img.setPrincipale(dto.isPrimary());
                img.setProdotto(prodotto); // COLLEGAMENTO AL PADRE
                return img;
            }).collect(Collectors.toList());
            prodotto.setImmagini(immagini);
        }

        // Associazione Specifiche (fondamentale settare il riferimento al prodotto padre)
        if (request.specifications() != null) {
            List<SpecificaProdotto> specifiche = request.specifications().stream().map(dto -> {
                SpecificaProdotto spec = new SpecificaProdotto();
                spec.setEtichetta(dto.label());
                spec.setValore(dto.value());
                spec.setProdotto(prodotto); // COLLEGAMENTO AL PADRE
                return spec;
            }).collect(Collectors.toList());
            prodotto.setSpecifiche(specifiche);
        }

        return prodotto;
    }

    // Da Entity a DTO (Lettura dal DB)
    public ProdottoResponse toResponse(Prodotto prodotto) {

        List<ImmagineProdottoDTO> immaginiDTO = prodotto.getImmagini() == null ? Collections.emptyList() :
                prodotto.getImmagini().stream()
                        .map(img -> new ImmagineProdottoDTO(img.getId(), img.getUrlImmagine(), img.getPrincipale()))
                        .collect(Collectors.toList());

        List<SpecificaProdottoDTO> specificheDTO = prodotto.getSpecifiche() == null ? Collections.emptyList() :
                prodotto.getSpecifiche().stream()
                        .map(spec -> new SpecificaProdottoDTO(spec.getId(), spec.getEtichetta(), spec.getValore()))
                        .collect(Collectors.toList());

        return new ProdottoResponse(
                prodotto.getId(),
                prodotto.getSku(),
                prodotto.getNome(),
                prodotto.getPrezzo(),
                prodotto.getQuantitaDisponibile(),
                prodotto.getAttivo(),
                prodotto.getCategoria() != null ? categoriaMapper.toResponse(prodotto.getCategoria()) : null,
                immaginiDTO,
                specificheDTO,
                prodotto.getCreatedAt(),
                prodotto.getUpdatedAt()
        );
    }
}