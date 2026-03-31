package com.nova.backend.dto.assistenza.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Richiesta di apertura di un ticket di supporto.
 */
@Data
public class TicketRequestDTO {

    @NotBlank(message = "L'oggetto del ticket è obbligatorio")
    private String oggetto;

    @NotNull(message = "La priorità è obbligatoria")
    private String priorita; // es. "ALTA", "MEDIA", "BASSA"

    @NotBlank(message = "Il messaggio iniziale è obbligatorio")
    private String messaggioIniziale;
}
