package com.nova.backend.dto.assistenza.risposta;

import java.time.Instant;

public record RecensioneResponse(
        Long id,
        Long userId,
        Long productId,
        Integer rating,
        String comment,
        Instant createdAt
) {
}