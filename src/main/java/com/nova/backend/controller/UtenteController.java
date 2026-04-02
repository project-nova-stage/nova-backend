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

    @PostMapping("/registrazione")
    public ResponseEntity<RispostaGenerica> registraUtente(@Valid @RequestBody RegistroUtenteDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.serviceUtente.registrazioneUtente(req));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDTO>> getTuttiGliUtenti() {
        List<UserResponseDTO> utenti = serviceUtente.findAllUsers();
        if (utenti.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(utenti);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(this.autenticazioneUtente.login(req));
    }

    @PostMapping("/logout")
    public ResponseEntity<RispostaGenerica> logout() {
        return ResponseEntity.ok(new RispostaGenerica("Logout effettuato con successo", null));
    }

    @PostMapping("/validazioneToken")
    public ResponseEntity<Map<String, Object>> validaToken() {
        Map<String, Object> risposta = new HashMap<>();
        risposta.put("tokenValido", true);
        return ResponseEntity.ok(risposta);
    }
}
