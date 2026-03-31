package com.nova.backend.dto.ordine.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Richiesta per aggiungere o aggiornare un prodotto nel carrello.
 */
@Data
public class CartItemRequestDTO {

    @NotNull(message = "L'ID prodotto è obbligatorio")
    private Long prodottoId;

    @NotNull(message = "La quantità è obbligatoria")
    @Min(value = 1, message = "La quantità minima è 1")
    private Integer quantita;
}
