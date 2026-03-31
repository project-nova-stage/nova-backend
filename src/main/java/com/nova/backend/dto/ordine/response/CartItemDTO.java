package com.nova.backend.dto.ordine.response;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Elemento nel carrello dell'utente.
 */
@Data
public class CartItemDTO {
    private Long productId;
    private String productNome;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}
