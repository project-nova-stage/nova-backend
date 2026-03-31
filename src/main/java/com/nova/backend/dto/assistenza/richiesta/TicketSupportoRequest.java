package com.nova.backend.dto.assistenza.richiesta;

import com.nova.backend.model.assistenza.PrioritaTicket;

public record TicketSupportoRequest(
        Long userId,
        String subject,
        PrioritaTicket priority
) {
}