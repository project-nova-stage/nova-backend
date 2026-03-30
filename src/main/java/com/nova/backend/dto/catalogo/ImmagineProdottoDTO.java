package com.nova.backend.dto.catalogo;

public record ImmagineProdottoDTO(
        Long id, // Opzionale in creazione
        String imageUrl,
        Boolean isPrimary
) {
}