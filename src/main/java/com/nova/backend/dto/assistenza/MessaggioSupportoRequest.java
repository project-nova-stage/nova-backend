package com.nova.backend.dto.assistenza;

public record MessaggioSupportoRequest(
        String content,
        Long ticketId,
        Long senderId
) {}
