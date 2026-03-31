package com.nova.backend.dto.iot.response;

import lombok.Data;

/**
 * Risposta con i dati di stato e anagrafica di un dispositivo IoT.
 */
@Data
public class DeviceResponseDTO {
    private Long id;
    private String deviceCode;
    private String macAddress;
    private String status;
    private String location;
    private String modelName;
}
