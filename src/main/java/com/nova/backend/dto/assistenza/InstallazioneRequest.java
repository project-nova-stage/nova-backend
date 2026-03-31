package com.nova.backend.dto.assistenza;

import java.time.LocalDateTime;

public record InstallazioneRequest(
        LocalDateTime scheduledDate,
        Long serviceRequestId,
        Long orderId,
        Long technicianId
) {}
