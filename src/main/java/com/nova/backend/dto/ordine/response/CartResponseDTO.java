package com.nova.backend.dto.ordine.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartResponseDTO {
    private Long id;
    private List<CartItemDTO> items;
    private BigDecimal totalAmount;
}
