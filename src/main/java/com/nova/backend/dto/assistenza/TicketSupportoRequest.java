package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.PrioritaTicket;

public record TicketSupportoRequest(
        Long userId,
        String subject,
        PrioritaTicket priority
) {
}