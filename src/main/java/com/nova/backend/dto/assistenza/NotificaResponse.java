package com.nova.backend.dto.assistenza;

import java.time.Instant;

public record NotificaResponse(
        Long id,
        Long userId,
        String titolo,
        String messaggio,
        Boolean letta,
        Instant dataCreazione
) {}
