package com.nova.backend.dto.catalogo.risposta;

import com.nova.backend.dto.catalogo.ImmagineProdottoDTO;
import com.nova.backend.dto.catalogo.SpecificaProdottoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Data Transfer Object per i Prodotti in uscita.
 * (Variabili in italiano).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoResponseDTO {
    
    private Long id;
    private String nome;
    private String descrizione;
    private BigDecimal prezzo;
    private String sku;
    private Integer quantitaDisponibile;
    
    // Riferimenti categoria
    private Long idCategoria;
    private String nomeCategoria;
    
    private Boolean attivo;
    private Instant dataCreazione;

    private List<ImmagineProdottoDTO> immagini;
    private List<SpecificaProdottoDTO> specifiche;
}
