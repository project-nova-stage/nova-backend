package com.nova.backend.dto.utente.richiesta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Richiesta di registrazione di un nuovo utente.
 */
@Data
public class RegistroUtenteDTO {

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    /** Opzionale: ruolo assegnato. Default CLIENTE se assente. */
    private String codiceRuolo;

    /** Opzionale: tipo cliente (B2C o B2B). Non applicabile ad ADMIN o TECNICO. */
    private String tipoCliente;
}
