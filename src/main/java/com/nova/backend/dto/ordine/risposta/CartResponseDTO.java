package com.nova.backend.dto.ordine.risposta;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * Risposta completa del carrello utente.
 */
@Data
public class CartResponseDTO {
    private Long id;
    private List<CartItemDTO> items;
    private BigDecimal totalAmount;
}
