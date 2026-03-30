package com.nova.backend.dto.catalogo;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record ProdottoResponse(
        Long id,
        String sku,
        String name,
        BigDecimal price,
        Integer stockQuantity,
        Boolean isActive,
        CategoriaResponse category, // Includiamo i dettagli base della categoria
        List<ImmagineProdottoDTO> images,
        List<SpecificaProdottoDTO> specifications,
        Instant createdAt,
        Instant updatedAt
) {
}