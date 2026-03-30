package com.nova.backend.dto.assistenza;

public record NotificaRequest(
        Long userId,
        String title,
        String message
) {
}