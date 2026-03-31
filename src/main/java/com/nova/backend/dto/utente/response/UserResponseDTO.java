package com.nova.backend.dto.utente.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String email;
    private String nome;
    private String cognome;
    private String ruolo;
    private Instant createdAt;
    private boolean attivo;
}
