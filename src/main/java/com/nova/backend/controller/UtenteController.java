package com.nova.backend.controller;

import com.nova.backend.dto.utente.request.LoginRequestDTO;
import com.nova.backend.dto.utente.request.RegistroUtenteDTO;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.service.utente.AutenticazioneUtente;
import com.nova.backend.service.utente.ServiceUtente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public Object registraUtente(@RequestBody RegistroUtenteDTO req) {
        return this.serviceUtente.registrazioneUtente(req);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Utente>> getTuttiGliUtenti() {
        List<Utente> utenti = serviceUtente.findAllUsers();
        if (utenti.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(utenti);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequestDTO req) {
        return this.autenticazioneUtente.login(req);
    }

    @PostMapping("/logout")
    public Object logout(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long idUtente) {
        return this.autenticazioneUtente.logout(token, idUtente);
    }

    @PostMapping("/validazioneToken")
    public Object validaToken(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long idUtente) {
        HashMap<String, Object> risposta = new HashMap<>();
        boolean valido = this.autenticazioneUtente.isTokenValid(token, idUtente);
        risposta.put("tokenValido", valido);
        return risposta;
    }
}
