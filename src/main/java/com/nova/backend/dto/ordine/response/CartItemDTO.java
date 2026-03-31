package com.nova.backend.dto.ordine.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemDTO {
    private Long productId;
    private String productNome;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}
