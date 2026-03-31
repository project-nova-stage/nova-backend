package com.nova.backend.dto.catalogo;

public record CategoriaResponse(
        Long id,
        String name,
        String slug,
        Long parentId
) {
}