package com.nova.backend.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO di risposta generica con un messaggio e un oggetto arbitrario come payload.
 */
@Getter
@Setter
public class RispostaGenerica {

    private String risposta;
    private Object record;

    public RispostaGenerica(String risposta, Object record) {
        this.risposta = risposta;
        this.record = record;
    }
}
