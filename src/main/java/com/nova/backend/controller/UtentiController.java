package com.nova.backend.controller;

import com.nova.backend.dto.utente.richiesta.AggiornaProfiloDTO;
import com.nova.backend.dto.utente.risposta.UserResponseDTO;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.service.utente.ServiceUtente;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/utenti")
public class UtentiController {

    private final ServiceUtente serviceUtente;

    public UtentiController(ServiceUtente serviceUtente) {
        this.serviceUtente = serviceUtente;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> recuperaProfilo(@AuthenticationPrincipal Utente utente) {
        return ResponseEntity.ok(serviceUtente.getProfiloUtente(utente.getId()));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> aggiornaProfilo(@AuthenticationPrincipal Utente utente,
                                                           @Valid @RequestBody AggiornaProfiloDTO req) {
        return ResponseEntity.ok(serviceUtente.aggiornaProfiloUtente(utente.getId(), req));
    }
}
