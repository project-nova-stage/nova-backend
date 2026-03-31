package com.nova.backend.dto.ordine.risposta;

import lombok.Data;
import java.math.BigDecimal;

/**
 * Voce di dettaglio di un ordine già effettuato.
 */
@Data
public class OrderItemDTO {
    private Long productId;
    private String productNome;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}
