package com.nova.backend.dto.assistenza;

import java.time.LocalDateTime;

public record InstallazioneRequest(
        Long serviceRequestId,
        Long orderId, // Può essere null
        Long technicianId,
        LocalDateTime scheduledDate
) {
}