package com.nova.backend.dto.assistenza.risposta;

import java.time.Instant;

public record MessaggioSupportoResponse(
        Long id,
        Long ticketId,
        Long senderId,
        String content,
        Instant createdAt
) {
}