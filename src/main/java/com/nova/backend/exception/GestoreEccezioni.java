package com.nova.backend.exception;

import com.nova.backend.dto.RispostaErrore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Gestore globale delle eccezioni: mappa le eccezioni applicative e di validazione
 * in risposte HTTP con status code appropriato in formato RispostaErrore.
 */
@RestControllerAdvice
public class GestoreEccezioni {

    // 1. Eccezioni personalizzate dell'applicazione
    @ExceptionHandler(EccezioneApplicativa.class)
    public ResponseEntity<RispostaErrore> gestisciEccezioneApplicativa(EccezioneApplicativa ex) {
        RispostaErrore corpo = new RispostaErrore(ex.getMessage(), ex.getStatus().value(), System.currentTimeMillis());
        return ResponseEntity.status(ex.getStatus()).body(corpo);
    }

    // 2. Errori di validazione (es. @Valid nei controller)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RispostaErrore> gestisciValidazione(MethodArgumentNotValidException ex) {
        String messaggio = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        RispostaErrore corpo = new RispostaErrore("Errore di validazione: " + messaggio, HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
        return ResponseEntity.badRequest().body(corpo);
    }

    // 3. Risorse non trovate (es. findById che fallisce)
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<RispostaErrore> gestisciRisorsaNonTrovata(NoSuchElementException ex) {
        RispostaErrore corpo = new RispostaErrore("Risorsa non trovata: " + ex.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
    }

    // 4. Accesso Negato (Spring Security)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RispostaErrore> gestisciAccessoNegato(AccessDeniedException ex) {
        RispostaErrore corpo = new RispostaErrore("Accesso negato: non hai i permessi per questa risorsa.", HttpStatus.FORBIDDEN.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(corpo);
    }

    // 5. Violazioni di integrità del database (es. unique constraint)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RispostaErrore> gestisciViolazioneIntegritaDati(DataIntegrityViolationException ex) {
        RispostaErrore corpo = new RispostaErrore("Errore di integrità dati: operazione non consentita sul database.", HttpStatus.CONFLICT.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpo);
    }

    // 6. Qualsiasi altra eccezione non gestita (Errore 500 generico)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RispostaErrore> gestisciEccezioneGenerica(Exception ex) {
        // Usa ex.getMessage() per debug, o loggalo in un logger reale
        RispostaErrore corpo = new RispostaErrore("Errore interno del server: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(corpo);
    }
}
