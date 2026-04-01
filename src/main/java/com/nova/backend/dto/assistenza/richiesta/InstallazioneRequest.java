package com.nova.backend.dto.assistenza.richiesta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InstallazioneRequest(
        @NotNull(message = "La richiesta servizio è obbligatoria")
        Long serviceRequestId,
        Long orderId, // Può essere null
        @NotNull(message = "Il tecnico assegnato è obbligatorio")
        Long technicianId,
        @NotNull(message = "La data pianificata è obbligatoria")
        @Future(message = "La data pianificata deve essere futura")
        LocalDateTime scheduledDate
) {
}