package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.richiesta.InstallazioneRequest;
import com.nova.backend.dto.assistenza.risposta.InstallazioneResponse;
import com.nova.backend.model.assistenza.StatoInstallazione;
import com.nova.backend.service.assistenza.InstallazioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/installazioni")
public class InstallazioneController {

    private final InstallazioneService service;

    public InstallazioneController(InstallazioneService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InstallazioneResponse> crea(@RequestBody InstallazioneRequest request) {
        return ResponseEntity.ok(service.pianificaInstallazione(request));
    }

    @GetMapping
    public ResponseEntity<List<InstallazioneResponse>> getAll() {
        return ResponseEntity.ok(service.ottieniTutte());
    }

    @GetMapping("/tecnico/{tecnicoId}")
    public ResponseEntity<List<InstallazioneResponse>> getByTecnico(@PathVariable Long tecnicoId) {
        return ResponseEntity.ok(service.ottieniPerTecnico(tecnicoId));
    }

    // Usiamo PATCH invece di PUT perché aggiorniamo un solo campo (lo stato)
    @PatchMapping("/{id}/stato")
    public ResponseEntity<InstallazioneResponse> cambiaStato(
            @PathVariable Long id,
            @RequestParam StatoInstallazione stato) {
        try {
            return ResponseEntity.ok(service.aggiornaStato(id, stato));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}