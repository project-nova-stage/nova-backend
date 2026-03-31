package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.richiesta.MessaggioSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.MessaggioSupportoResponse;
import com.nova.backend.service.assistenza.MessaggioSupportoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/messaggi")
public class MessaggioSupportoController {

    private final MessaggioSupportoService service;

    public MessaggioSupportoController(MessaggioSupportoService service) {
        this.service = service;
    }

    // Aggiunge un nuovo messaggio a un ticket
    @PostMapping
    public ResponseEntity<MessaggioSupportoResponse> inviaMessaggio(@RequestBody MessaggioSupportoRequest request) {
        return ResponseEntity.ok(service.aggiungiMessaggio(request));
    }

    // Recupera tutta la chat di un ticket specifico (es. /api/v1/assistenza/messaggi/ticket/1)
    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<MessaggioSupportoResponse>> getCronologiaTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(service.ottieniMessaggiPerTicket(ticketId));
    }
}