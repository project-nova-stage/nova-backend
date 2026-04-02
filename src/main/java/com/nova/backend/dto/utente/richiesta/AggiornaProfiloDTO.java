package com.nova.backend.dto.utente.richiesta;

import jakarta.validation.constraints.Email;
import lombok.Data;

/**
 * DTO per aggiornare il profilo utente corrente.
 */
@Data
public class AggiornaProfiloDTO {

    @Email(message = "Formato email non valido")
    private String email;

    private String nome;

    private String cognome;

    private String tipoCliente;
}
