package com.nova.backend.dto.utente.risposta;

import lombok.Data;
import java.time.Instant;

/**
 * Risposta profilo utente.
 */
@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private String nome;
    private String cognome;
    private String ruolo;
    private Instant createdAt;
    private boolean attivo;
}
