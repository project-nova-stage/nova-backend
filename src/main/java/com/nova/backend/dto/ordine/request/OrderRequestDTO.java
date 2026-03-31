package com.nova.backend.dto.ordine.request;

import lombok.Data;

/**
 * Richiesta di creazione ordine (Checkout).
 */
@Data
public class OrderRequestDTO {
    private Long cartId;
    private String paymentMethod;
}
