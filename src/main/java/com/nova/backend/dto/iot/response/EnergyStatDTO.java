package com.nova.backend.dto.iot.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EnergyStatDTO {
    private LocalDate date;
    private BigDecimal consumptionKwh;
}
