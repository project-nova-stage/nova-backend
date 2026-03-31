package com.nova.backend.dto.assistenza.risposta;

import com.nova.backend.model.assistenza.StatoServizio;
import com.nova.backend.model.assistenza.TipoProgetto;

import java.time.Instant;

public record RichiestaServizioResponse(
        Long id,
        Long userId,
        TipoProgetto projectType,
        String description,
        StatoServizio status,
        Instant createdAt
) {
}