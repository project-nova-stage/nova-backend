package com.nova.backend.dto.catalogo;

import lombok.Data;

/**
 * DTO per le specifiche tecniche dei prodotti (es. "CPU", "RAM", "Autonomia").
 */
@Data
public class SpecificaProdottoDTO {
    private Long id;
    private String chiave;
    private String valore;
}