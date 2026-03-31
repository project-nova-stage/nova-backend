package com.nova.backend.dto.assistenza.richiesta;

public record RecensioneRequest(
        Long userId,
        Long productId,
        Integer rating,
        String comment
) {
}
