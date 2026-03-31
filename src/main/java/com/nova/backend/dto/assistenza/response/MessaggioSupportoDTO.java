package com.nova.backend.dto.assistenza.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessaggioSupportoDTO {
    private Long id;
    private String senderName;
    private String content;
    private Instant createdAt;
}
