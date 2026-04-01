package com.nova.backend.controller;

import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.richiesta.LoginRequestDTO;
import com.nova.backend.dto.utente.richiesta.RegistroUtenteDTO;
import com.nova.backend.dto.utente.risposta.UserResponseDTO;
import com.nova.backend.service.utente.AutenticazioneUtente;
import com.nova.backend.service.utente.ServiceUtente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller REST per la gestione delle operazioni relative agli utenti.
 * Espone gli endpoint per registrazione, login, logout e recupero profili.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utente")
public class UtenteController {

    private final ServiceUtente serviceUtente;
    private final AutenticazioneUtente autenticazioneUtente;

    public UtenteController(ServiceUtente serviceUtente, AutenticazioneUtente autenticazioneUtente) {
        this.serviceUtente = serviceUtente;
        this.autenticazioneUtente = autenticazioneUtente;
    }

    /**
     * Registra un nuovo utente a sistema.
     * @param req DTO contenente i dati anagrafici e credenziali del nuovo utente.
     * @return L'esito della registrazione. Restituisce HTTP 201 Created.
     */
    @PostMapping("/registrazione")
    public ResponseEntity<RispostaGenerica> registraUtente(@Valid @RequestBody RegistroUtenteDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.serviceUtente.registrazioneUtente(req));
    }

    /**
     * Ottiene la lista completa di tutti gli utenti registrati.
     * @return Una lista di UserResponseDTO oppure HTTP 204 No Content se vuota.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDTO>> getTuttiGliUtenti() {
        List<UserResponseDTO> utenti = serviceUtente.findAllUsers();
        if (utenti.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(utenti);
    }

    /**
     * Autentica un utente verificandone le credenziali.
     * @param req DTO contenente username/email e password.
     * @return Una mappa con informazioni di autenticazione e token generato.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(this.autenticazioneUtente.login(req));
    }

    /**
     * Invalida la sessione attiva e disconnette l'utente.
     * @param token Il token di sicurezza attivo passato nell'header.
     * @param idUtente ID numerico dell'utente che richiede il logout.
     * @return Un messaggio generico di conferma.
     */
    @PostMapping("/logout")
    public ResponseEntity<RispostaGenerica> logout(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long idUtente) {
        return ResponseEntity.ok(this.autenticazioneUtente.logout(token, idUtente));
    }

    /**
     * Controlla l'integrità e la validità del token per le rotte private.
     * @param token Il token di sicurezza da analizzare.
     * @param idUtente L'utente associato da verificare.
     * @return Mappa booleana con l'esito della validazione.
     */
    @PostMapping("/validazioneToken")
    public ResponseEntity<Map<String, Object>> validaToken(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long idUtente) {
        Map<String, Object> risposta = new HashMap<>();
        risposta.put("tokenValido", this.autenticazioneUtente.isTokenValid(token, idUtente));
        return ResponseEntity.ok(risposta);
    }
}
