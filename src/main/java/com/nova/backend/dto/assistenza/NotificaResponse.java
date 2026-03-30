package com.nova.backend.dto.assistenza;

import java.time.Instant;

public record NotificaResponse(
        Long id,
        Long userId,
        String title,
        String message,
        Boolean isRead,
        Instant createdAt
) {
}