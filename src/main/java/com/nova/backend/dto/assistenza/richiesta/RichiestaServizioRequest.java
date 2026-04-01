package com.nova.backend.dto.assistenza.richiesta;

import com.nova.backend.model.assistenza.TipoProgetto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RichiestaServizioRequest(
        @NotNull(message = "L'utente è obbligatorio")
        Long userId,
        @NotNull(message = "Il tipo progetto è obbligatorio")
        TipoProgetto projectType,
        @NotBlank(message = "La descrizione è obbligatoria")
        String description
) {
}