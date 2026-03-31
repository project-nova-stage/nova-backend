package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.richiesta.TicketSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.TicketSupportoResponse;
import com.nova.backend.service.assistenza.TicketSupportoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/ticket")
public class TicketSupportoController {

    private final TicketSupportoService service;

    public TicketSupportoController(TicketSupportoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TicketSupportoResponse> crea(@RequestBody TicketSupportoRequest request) {
        return ResponseEntity.ok(service.creaTicket(request));
    }

    @GetMapping
    public ResponseEntity<List<TicketSupportoResponse>> getAll() {
        return ResponseEntity.ok(service.ottieniTutti());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketSupportoResponse> getById(@PathVariable Long id) {
        return service.ottieniPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketSupportoResponse> aggiorna(@PathVariable Long id, @RequestBody TicketSupportoRequest request) {
        try {
            return ResponseEntity.ok(service.aggiornaTicket(id, request));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.eliminaTicket(id);
        return ResponseEntity.noContent().build();
    }
}