package com.nova.backend.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO di risposta per gli errori applicativi (es. login fallito, email già in uso).
 */
@Getter
@Setter
public class RispostaErrore {

    private String messaggio;
    private int stato;
    private long timestamp;

    public RispostaErrore(String messaggio, int stato, long timestamp) {
        this.messaggio = messaggio;
        this.stato = stato;
        this.timestamp = timestamp;
    }
}
