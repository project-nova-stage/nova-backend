package com.nova.backend.dto.assistenza;

import com.nova.backend.model.assistenza.StatoInstallazione;

import java.time.LocalDateTime;

public record InstallazioneResponse(
        Long id,
        Long serviceRequestId,
        Long orderId,
        Long technicianId,
        LocalDateTime scheduledDate,
        StatoInstallazione status
) {
}