package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.RichiestaServizioRequest;
import com.nova.backend.dto.assistenza.RichiestaServizioResponse;
import com.nova.backend.model.assistenza.StatoServizio;
import com.nova.backend.service.assistenza.RichiestaServizioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/richieste")
public class RichiestaServizioController {

    private final RichiestaServizioService service;

    public RichiestaServizioController(RichiestaServizioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RichiestaServizioResponse> crea(@RequestBody RichiestaServizioRequest request) {
        return ResponseEntity.ok(service.creaRichiesta(request));
    }

    @GetMapping
    public ResponseEntity<List<RichiestaServizioResponse>> getAll() {
        return ResponseEntity.ok(service.ottieniTutte());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RichiestaServizioResponse> getById(@PathVariable Long id) {
        return service.ottieniPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<RichiestaServizioResponse>> getByUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(service.ottieniPerUtente(utenteId));
    }

    // Anche qui PATCH per aggiornare solo lo stato della pratica
    @PatchMapping("/{id}/stato")
    public ResponseEntity<RichiestaServizioResponse> cambiaStato(
            @PathVariable Long id,
            @RequestParam StatoServizio stato) {
        try {
            return ResponseEntity.ok(service.aggiornaStato(id, stato));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}