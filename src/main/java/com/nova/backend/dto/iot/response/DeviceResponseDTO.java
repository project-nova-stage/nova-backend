package com.nova.backend.dto.iot.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponseDTO {
    private Long id;
    private String deviceCode;
    private String macAddress;
    private String status;
    private String location;
    private String modelName;
}
