package com.nova.backend.dto.catalogo;

import lombok.Data;

/**
 * DTO per le immagini dei prodotti.
 */
@Data
public class ImmagineProdottoDTO {
    private Long id;
    private String urlImmagine;
    private Boolean principale;
}