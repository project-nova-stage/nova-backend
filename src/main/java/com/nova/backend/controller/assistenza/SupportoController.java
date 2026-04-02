package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.richiesta.MessaggioSupportoRequest;
import com.nova.backend.dto.assistenza.richiesta.TicketSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.MessaggioSupportoResponse;
import com.nova.backend.dto.assistenza.risposta.TicketSupportoResponse;
import com.nova.backend.model.assistenza.StatoTicket;
import com.nova.backend.service.assistenza.SupportoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/supporto")
public class SupportoController {

    private final SupportoService service;

    public SupportoController(SupportoService service) {
        this.service = service;
    }

    // Ticket
    @PostMapping("/ticket")
    public ResponseEntity<TicketSupportoResponse> creaTicket(@RequestBody TicketSupportoRequest request) {
        return ResponseEntity.ok(service.creaTicket(request));
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<TicketSupportoResponse>> getTuttiITicket() {
        return ResponseEntity.ok(service.ottieniTuttiITicket());
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketSupportoResponse> getTicketById(@PathVariable Long id) {
        return service.ottieniTicketPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<TicketSupportoResponse> aggiornaTicket(@PathVariable Long id, @RequestBody TicketSupportoRequest request) {
        try {
            return ResponseEntity.ok(service.aggiornaTicket(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Void> eliminaTicket(@PathVariable Long id) {
        service.eliminaTicket(id);
        return ResponseEntity.noContent().build();
    }

    // Messaggi
    @PostMapping("/messaggi")
    public ResponseEntity<MessaggioSupportoResponse> aggiungiMessaggio(@RequestBody MessaggioSupportoRequest request) {
        return ResponseEntity.ok(service.aggiungiMessaggio(request));
    }

    @GetMapping("/messaggi/ticket/{ticketId}")
    public ResponseEntity<List<MessaggioSupportoResponse>> getMessaggiPerTicket(@PathVariable Long ticketId) {
        return ResponseEntity.ok(service.ottieniMessaggiPerTicket(ticketId));
    }

    // Esempio: aggiornare lo stato 
    @PatchMapping("/ticket/{id}/stato")
    public ResponseEntity<TicketSupportoResponse> cambiaStatoTicket(
            @PathVariable Long id,
            @RequestParam StatoTicket stato) {
        return ResponseEntity.badRequest().build();
    }
}
