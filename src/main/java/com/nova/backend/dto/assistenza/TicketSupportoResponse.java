package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.PrioritaTicket;
import com.nova.backend.model.assistenza.StatoTicket;

public record TicketSupportoResponse(
        Long id,
        Long userId,
        String oggetto,
        PrioritaTicket priorita,
        StatoTicket stato
) {}
