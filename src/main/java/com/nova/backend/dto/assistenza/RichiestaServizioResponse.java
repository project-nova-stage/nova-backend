package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.StatoServizio;
import com.nova.backend.model.assistenza.TipoProgetto;

import java.time.Instant;

public record RichiestaServizioResponse(
        Long id,
        Long userId,
        TipoProgetto tipoProgetto,
        String descrizione,
        StatoServizio stato,
        Instant dataCreazione
) {}
