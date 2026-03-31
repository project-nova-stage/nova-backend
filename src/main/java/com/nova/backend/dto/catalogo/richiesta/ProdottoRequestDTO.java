package com.nova.backend.dto.catalogo.richiesta;

import com.nova.backend.dto.catalogo.ImmagineProdottoDTO;
import com.nova.backend.dto.catalogo.SpecificaProdottoDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object per la creazione e aggiornamento dei Prodotti.
 * (Nomi e variabili in italiano secondo gli standard del progetto).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdottoRequestDTO {

    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    private String nome;

    private String descrizione;

    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal prezzo;

    @NotBlank(message = "Lo SKU è obbligatorio")
    private String sku;

    private Integer quantitaDisponibile;

    @NotNull(message = "L'ID della categoria è obbligatorio")
    private Long categoriaId;

    private Boolean attivo;

    private List<ImmagineProdottoDTO> immagini;
    private List<SpecificaProdottoDTO> specifiche;
}
