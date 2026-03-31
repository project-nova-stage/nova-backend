package com.nova.backend.dto.assistenza;

import java.time.Instant;

public record MessaggioSupportoResponse(
        Long id,
        Long ticketId,
        Long senderId,
        String contenuto,
        Instant dataCreazione
) {}
