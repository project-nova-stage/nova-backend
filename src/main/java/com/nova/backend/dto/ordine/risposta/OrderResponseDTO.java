package com.nova.backend.dto.ordine.risposta;

import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Risposta con i dati completi di un ordine.
 */
@Data
public class OrderResponseDTO {
    private Long id;
    private String numeroOrdine;
    private String stato;
    private BigDecimal importoTotale;
    private Instant dataOrdine;
    private List<OrderItemDTO> items;
}
