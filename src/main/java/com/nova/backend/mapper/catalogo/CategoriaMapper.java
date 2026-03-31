package com.nova.backend.mapper.catalogo;

import com.nova.backend.dto.catalogo.CategoriaDTO;
import com.nova.backend.model.catalogo.Categoria;
import org.springframework.stereotype.Component;

/**
 * Mapper per le categorie del catalogo.
 */
@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;

        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        
        return entity;
    }

    public CategoriaDTO toResponse(Categoria entity) {
        if (entity == null) return null;

        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        
        return dto;
    }
}