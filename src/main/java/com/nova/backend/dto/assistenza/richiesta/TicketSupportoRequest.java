package com.nova.backend.dto.assistenza.richiesta;

import com.nova.backend.model.assistenza.PrioritaTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketSupportoRequest(
        @NotNull(message = "L'utente è obbligatorio")
        Long userId,
        @NotBlank(message = "L'oggetto del ticket è obbligatorio")
        String subject,
        @NotNull(message = "La priorità è obbligatoria")
        PrioritaTicket priority
) {
}