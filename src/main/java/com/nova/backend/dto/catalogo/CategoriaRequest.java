package com.nova.backend.dto.catalogo;

public record CategoriaRequest(
        String name,
        String slug,
        Long parentId // Può essere null se è una categoria root
) {
}