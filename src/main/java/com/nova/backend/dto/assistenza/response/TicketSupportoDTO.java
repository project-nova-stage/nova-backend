package com.nova.backend.dto.assistenza.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TicketSupportoDTO {
    private Long id;
    private String subject;
    private String priority;
    private String status;
    private Instant createdAt;
}
