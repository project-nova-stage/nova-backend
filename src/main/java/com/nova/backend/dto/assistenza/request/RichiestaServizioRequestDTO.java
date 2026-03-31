package com.nova.backend.dto.assistenza.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Richiesta di sopralluogo o preventivo per un servizio Nova.
 */
@Data
public class RichiestaServizioRequestDTO {

    @NotNull(message = "Il tipo di progetto è obbligatorio")
    private String tipoProgetto; // es. "FOTOVOLTAICO", "DOMOTICA", "SICUREZZA"

    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;
}
