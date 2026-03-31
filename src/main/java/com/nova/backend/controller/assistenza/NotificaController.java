package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.richiesta.NotificaRequest;
import com.nova.backend.dto.assistenza.risposta.NotificaResponse;
import com.nova.backend.service.assistenza.NotificaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/notifiche")
public class NotificaController {

    private final NotificaService service;

    public NotificaController(NotificaService service) {
        this.service = service;
    }

    // Endpoint utile per i test, ma di solito le notifiche vengono create internamente dagli altri Service
    @PostMapping
    public ResponseEntity<NotificaResponse> crea(@RequestBody NotificaRequest request) {
        return ResponseEntity.ok(service.inviaNotifica(request));
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<NotificaResponse>> getAllByUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(service.ottieniPerUtente(utenteId));
    }

    @GetMapping("/utente/{utenteId}/non-lette")
    public ResponseEntity<List<NotificaResponse>> getUnreadByUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(service.ottieniNonLette(utenteId));
    }

    @PatchMapping("/{id}/leggi")
    public ResponseEntity<Void> segnaLetta(@PathVariable Long id) {
        service.segnaComeLetta(id);
        return ResponseEntity.noContent().build();
    }
}