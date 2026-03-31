package com.nova.backend.dto.iot.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DeviceLogDTO {
    private String eventType;
    private String message;
    private Instant createdAt;
}
