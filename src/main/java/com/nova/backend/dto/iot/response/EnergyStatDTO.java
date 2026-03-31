package com.nova.backend.dto.iot.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Risposta con i dati di telemetria energetica giornaliera.
 */
@Data
public class EnergyStatDTO {
    private LocalDate date;
    private BigDecimal consumptionKwh;
}
