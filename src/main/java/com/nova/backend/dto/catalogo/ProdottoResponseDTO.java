package com.nova.backend.dto.catalogo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ProdottoResponseDTO {
    private Long id;
    private String sku;
    private String nome;
    private BigDecimal prezzo;
    private Integer quantitaDisponibile;
    private Boolean attivo;
    private Instant dataCreazione;
    private Long idCategoria;
    private String nomeCategoria;
    private List<ImmagineProdottoDTO> immagini;
    private List<SpecificaProdottoDTO> specifiche;
}
