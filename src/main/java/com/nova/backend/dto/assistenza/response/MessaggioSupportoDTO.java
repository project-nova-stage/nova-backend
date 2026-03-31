package com.nova.backend.dto.assistenza.response;

import lombok.Data;
import java.time.Instant;

/**
 * Risposta con i dati di un singolo messaggio di supporto.
 */
@Data
public class MessaggioSupportoDTO {
    private Long id;
    private String senderName;
    private String content;
    private Instant createdAt;
}
