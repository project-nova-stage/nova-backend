package com.nova.backend.dto.utente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestRegistrazioneDTO {
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String ruolo;
    private String tipoCliente;
}
