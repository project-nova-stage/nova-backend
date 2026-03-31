package com.nova.backend.dto.assistenza.response;

import lombok.Data;
import java.time.Instant;

/**
 * Risposta con i dati di una richiesta di servizio/installazione.
 */
@Data
public class RichiestaServizioDTO {
    private Long id;
    private String projectType;
    private String status;
    private Instant createdAt;
}
