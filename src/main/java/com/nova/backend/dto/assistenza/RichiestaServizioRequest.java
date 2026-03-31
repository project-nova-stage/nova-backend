package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.TipoProgetto;

public record RichiestaServizioRequest(
        TipoProgetto projectType,
        String description,
        Long userId
) {}
