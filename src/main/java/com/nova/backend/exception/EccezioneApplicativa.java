package com.nova.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * Eccezione applicativa che trasporta il messaggio di errore e lo status HTTP da restituire al client.
 */
public class EccezioneApplicativa extends RuntimeException {

    private final HttpStatus status;

    public EccezioneApplicativa(String messaggio, HttpStatus status) {
        super(messaggio);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
