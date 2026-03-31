package com.nova.backend.dto.assistenza.risposta;

import lombok.Data;
import java.time.Instant;

/**
 * Risposta con i dati di un ticket di supporto.
 */
@Data
public class TicketSupportoDTO {
    private Long id;
    private String subject;
    private String priority;
    private String status;
    private Instant createdAt;
}
