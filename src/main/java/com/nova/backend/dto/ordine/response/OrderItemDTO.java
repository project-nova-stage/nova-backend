package com.nova.backend.dto.ordine.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private String productNome;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}
