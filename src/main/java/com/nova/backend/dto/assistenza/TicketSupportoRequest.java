package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.PrioritaTicket;

public record TicketSupportoRequest(
        String subject,
        PrioritaTicket priority,
        Long userId
) {}
