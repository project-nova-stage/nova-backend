package com.nova.backend.mapper.catalogo;

import com.nova.backend.dto.catalogo.CategoriaRequest;
import com.nova.backend.dto.catalogo.CategoriaResponse;
import com.nova.backend.model.catalogo.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.name());
        categoria.setSlug(request.slug());

        if (request.parentId() != null) {
            Categoria padre = new Categoria();
            padre.setId(request.parentId());
            categoria.setCategoriaPadre(padre);
        }

        return categoria;
    }

    public CategoriaResponse toResponse(Categoria categoria) {
        Long parentId = (categoria.getCategoriaPadre() != null) ? categoria.getCategoriaPadre().getId() : null;

        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getSlug(),
                parentId
        );
    }
}