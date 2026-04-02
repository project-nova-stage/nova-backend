package com.nova.backend.controller;

import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.richiesta.LoginRequestDTO;
import com.nova.backend.dto.utente.richiesta.RegistroUtenteDTO;
import com.nova.backend.service.utente.AutenticazioneUtente;
import com.nova.backend.service.utente.ServiceUtente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ServiceUtente serviceUtente;
    private final AutenticazioneUtente autenticazioneUtente;

    public AuthController(ServiceUtente serviceUtente, AutenticazioneUtente autenticazioneUtente) {
        this.serviceUtente = serviceUtente;
        this.autenticazioneUtente = autenticazioneUtente;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(autenticazioneUtente.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegistroUtenteDTO req) {
        serviceUtente.registrazioneUtente(req);

        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail(req.getEmail());
        loginRequest.setPassword(req.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED).body(autenticazioneUtente.login(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<RispostaGenerica> logout() {
        return ResponseEntity.ok(new RispostaGenerica("Logout effettuato con successo", null));
    }
}
