package com.nova.backend.DTO;

import lombok.Getter;
import lombok.Setter;

//REQUEST USATA PER LA REGISTRAZIONE DELL'UTENTE
@Getter
@Setter
public class RequestRegistrazione {

    String nome;
    String cognome;
    String ruolo;
    String tipoCliente;
    String email;
    String password;

}
