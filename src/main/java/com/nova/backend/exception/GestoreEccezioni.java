package com.nova.backend.exception;

import com.nova.backend.dto.RispostaErrore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Gestore globale delle eccezioni: mappa le eccezioni applicative e di validazione
 * in risposte HTTP con status code appropriato.
 */
@RestControllerAdvice
public class GestoreEccezioni {

    @ExceptionHandler(EccezioneApplicativa.class)
    public ResponseEntity<RispostaErrore> gestisciEccezioneApplicativa(EccezioneApplicativa ex) {
        RispostaErrore corpo = new RispostaErrore(ex.getMessage(), ex.getStatus().value(), System.currentTimeMillis());
        return ResponseEntity.status(ex.getStatus()).body(corpo);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RispostaErrore> gestisciValidazione(MethodArgumentNotValidException ex) {
        String messaggio = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        RispostaErrore corpo = new RispostaErrore(messaggio, 400, System.currentTimeMillis());
        return ResponseEntity.badRequest().body(corpo);
    }
}
