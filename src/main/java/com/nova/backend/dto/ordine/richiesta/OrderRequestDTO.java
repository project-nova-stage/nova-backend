package com.nova.backend.dto.ordine.richiesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Richiesta di creazione ordine (Checkout).
 */
@Data
public class OrderRequestDTO {
    @NotNull(message = "L'ID del carrello è obbligatorio")
    private Long cartId;

    @NotBlank(message = "Il metodo di pagamento è obbligatorio")
    private String paymentMethod;
}
