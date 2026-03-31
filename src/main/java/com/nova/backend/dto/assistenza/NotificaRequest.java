package com.nova.backend.dto.assistenza;

public record NotificaRequest(
        String title,
        String message,
        Long userId
) {}
