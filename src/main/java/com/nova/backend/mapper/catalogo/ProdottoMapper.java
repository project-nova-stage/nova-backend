package com.nova.backend.mapper.catalogo;

import com.nova.backend.dto.catalogo.richiesta.ProdottoRequestDTO;
import com.nova.backend.dto.catalogo.risposta.ProdottoResponseDTO;
import com.nova.backend.dto.catalogo.ImmagineProdottoDTO;
import com.nova.backend.dto.catalogo.SpecificaProdottoDTO;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.catalogo.ImmagineProdotto;
import com.nova.backend.model.catalogo.SpecificaProdotto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Mapper per i prodotti del catalogo.
 * Gestisce conversioni complesse includendo immagini e specifiche.
 */
@Component
public class ProdottoMapper {

    public Prodotto toEntity(ProdottoRequestDTO dto) {
        if (dto == null) return null;

        Prodotto entity = new Prodotto();
        entity.setSku(dto.getSku());
        entity.setNome(dto.getNome());
        entity.setDescrizione(dto.getDescrizione());
        entity.setPrezzo(dto.getPrezzo());
        entity.setQuantitaDisponibile(dto.getQuantitaDisponibile());
        entity.setAttivo(dto.getAttivo() != null ? dto.getAttivo() : true);

        if (dto.getImmagini() != null) {
            entity.setImmagini(dto.getImmagini().stream()
                    .map(imgDto -> {
                        ImmagineProdotto img = new ImmagineProdotto();
                        img.setUrlImmagine(imgDto.getUrlImmagine());
                        img.setPrincipale(imgDto.getPrincipale());
                        img.setProdotto(entity);
                        return img;
                    }).collect(Collectors.toList()));
        }

        if (dto.getSpecifiche() != null) {
            entity.setSpecifiche(dto.getSpecifiche().stream()
                    .map(specDto -> {
                        SpecificaProdotto spec = new SpecificaProdotto();
                        spec.setEtichetta(specDto.getChiave());
                        spec.setValore(specDto.getValore());
                        spec.setProdotto(entity);
                        return spec;
                    }).collect(Collectors.toList()));
        }

        return entity;
    }

    public ProdottoResponseDTO toResponse(Prodotto entity) {
        if (entity == null) return null;

        ProdottoResponseDTO dto = new ProdottoResponseDTO();
        dto.setId(entity.getId());
        dto.setSku(entity.getSku());
        dto.setNome(entity.getNome());
        dto.setDescrizione(entity.getDescrizione());
        dto.setPrezzo(entity.getPrezzo());
        dto.setQuantitaDisponibile(entity.getQuantitaDisponibile());
        dto.setAttivo(entity.getAttivo());
        dto.setDataCreazione(entity.getCreatedAt());
        
        if (entity.getCategoria() != null) {
            dto.setIdCategoria(entity.getCategoria().getId());
            dto.setNomeCategoria(entity.getCategoria().getNome());
        }

        if (entity.getImmagini() != null) {
            dto.setImmagini(entity.getImmagini().stream()
                    .map(img -> {
                        ImmagineProdottoDTO imgDto = new ImmagineProdottoDTO();
                        imgDto.setId(img.getId());
                        imgDto.setUrlImmagine(img.getUrlImmagine());
                        imgDto.setPrincipale(img.getPrincipale());
                        return imgDto;
                    }).collect(Collectors.toList()));
        } else {
            dto.setImmagini(Collections.emptyList());
        }

        if (entity.getSpecifiche() != null) {
            dto.setSpecifiche(entity.getSpecifiche().stream()
                    .map(spec -> {
                        SpecificaProdottoDTO specDto = new SpecificaProdottoDTO();
                        specDto.setId(spec.getId());
                        specDto.setChiave(spec.getEtichetta());
                        specDto.setValore(spec.getValore());
                        return specDto;
                    }).collect(Collectors.toList()));
        } else {
            dto.setSpecifiche(Collections.emptyList());
        }

        return dto;
    }
}