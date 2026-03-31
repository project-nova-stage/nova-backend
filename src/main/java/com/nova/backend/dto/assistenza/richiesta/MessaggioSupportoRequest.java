package com.nova.backend.dto.assistenza.richiesta;

public record MessaggioSupportoRequest(
        Long ticketId,
        Long senderId,
        String content
) {
}