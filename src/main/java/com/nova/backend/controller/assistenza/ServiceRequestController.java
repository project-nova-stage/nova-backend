package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.richiesta.RichiestaServizioRequest;
import com.nova.backend.dto.assistenza.risposta.RichiestaServizioResponse;
import com.nova.backend.model.assistenza.StatoServizio;
import com.nova.backend.service.assistenza.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService service;

    public ServiceRequestController(ServiceRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RichiestaServizioResponse> creaRichiesta(@RequestBody RichiestaServizioRequest request) {
        return ResponseEntity.ok(service.createRequest(request));
    }

    @GetMapping
    public ResponseEntity<List<RichiestaServizioResponse>> getAllRichieste() {
        return ResponseEntity.ok(service.getAllRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RichiestaServizioResponse> getRichiestaById(@PathVariable Long id) {
        return service.getRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<RichiestaServizioResponse>> getRichiestePerUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(service.getRequestsByUser(utenteId));
    }

    @PatchMapping("/{id}/stato")
    public ResponseEntity<RichiestaServizioResponse> cambiaStato(@PathVariable Long id, @RequestParam StatoServizio stato) {
        try {
            return ResponseEntity.ok(service.updateStatus(id, stato));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaRichiesta(@PathVariable Long id) {
        try {
            service.deleteRequest(id);
            return ResponseEntity.noContent().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.status(501).build();
        }
    }
}
