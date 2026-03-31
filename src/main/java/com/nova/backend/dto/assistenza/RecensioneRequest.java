package com.nova.backend.dto.assistenza;

public record RecensioneRequest(
        Integer rating,
        String comment,
        Long userId,
        Long productId
) {}
