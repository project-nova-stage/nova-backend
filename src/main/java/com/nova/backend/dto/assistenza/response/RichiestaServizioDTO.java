package com.nova.backend.dto.assistenza.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RichiestaServizioDTO {
    private Long id;
    private String projectType;
    private String status;
    private Instant createdAt;
}
