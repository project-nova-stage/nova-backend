package com.nova.backend.dto.utente.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroUtenteDTO {
    private String email;
    private String password;
    private String nome;
    private String cognome;
}
