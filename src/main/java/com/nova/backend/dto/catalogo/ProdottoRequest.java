package com.nova.backend.dto.catalogo;

import java.math.BigDecimal;
import java.util.List;

public record ProdottoRequest(
        String sku,
        String name,
        BigDecimal price,
        Integer stockQuantity,
        Boolean isActive,
        Long categoryId,
        List<ImmagineProdottoDTO> images,
        List<SpecificaProdottoDTO> specifications
) {
}