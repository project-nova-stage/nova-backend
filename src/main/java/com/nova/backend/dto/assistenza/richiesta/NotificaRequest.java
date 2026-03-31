package com.nova.backend.dto.assistenza.richiesta;

public record NotificaRequest(
        Long userId,
        String title,
        String message
) {
}