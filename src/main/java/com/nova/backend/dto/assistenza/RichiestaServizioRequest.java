package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.TipoProgetto;

public record RichiestaServizioRequest(
        Long userId,
        TipoProgetto projectType,
        String description
) {
}