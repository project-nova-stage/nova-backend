package com.nova.backend.dto.iot.response;

import lombok.Data;
import java.time.Instant;

/**
 * DTO per la visualizzazione di un evento nel log del dispositivo.
 */
@Data
public class DeviceLogDTO {
    private String eventType;
    private String message;
    private Instant createdAt;
}
