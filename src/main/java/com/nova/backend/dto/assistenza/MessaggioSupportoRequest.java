package com.nova.backend.dto.assistenza;

public record MessaggioSupportoRequest(
        Long ticketId,
        Long senderId,
        String content
) {
}