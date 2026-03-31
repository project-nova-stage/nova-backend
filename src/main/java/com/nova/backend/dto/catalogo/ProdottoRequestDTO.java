package com.nova.backend.dto.catalogo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProdottoRequestDTO {
    private String sku;
    private String nome;
    private BigDecimal prezzo;
    private Integer quantitaDisponibile;
    private Boolean attivo;
    private List<ImmagineProdottoDTO> immagini;
    private List<SpecificaProdottoDTO> specifiche;
}
