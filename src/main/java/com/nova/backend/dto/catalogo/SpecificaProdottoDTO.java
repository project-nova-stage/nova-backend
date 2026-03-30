package com.nova.backend.dto.catalogo;

public record SpecificaProdottoDTO(
        Long id, // Opzionale per nuove specifiche, necessario per aggiornamenti
        String label,
        String value
) {
}