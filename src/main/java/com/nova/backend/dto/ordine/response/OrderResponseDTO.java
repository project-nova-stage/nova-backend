package com.nova.backend.dto.ordine.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {
    private Long id;
    private String numeroOrdine;
    private String stato;
    private BigDecimal importoTotale;
    private Instant dataOrdine;
    private List<OrderItemDTO> items;
}
