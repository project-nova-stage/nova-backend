package com.nova.backend.dto.assistenza;

public record RecensioneRequest(
        Long userId,
        Long productId,
        Integer rating,
        String comment
) {
}
