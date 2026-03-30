package com.nova.backend.controller.assistenza;

import com.nova.backend.dto.assistenza.RecensioneRequest;
import com.nova.backend.dto.assistenza.RecensioneResponse;
import com.nova.backend.service.assistenza.RecensioneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assistenza/recensioni")
public class RecensioneController {

    private final RecensioneService service;

    public RecensioneController(RecensioneService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RecensioneResponse> crea(@RequestBody RecensioneRequest request) {
        return ResponseEntity.ok(service.creaRecensione(request));
    }

    @GetMapping("/prodotto/{prodottoId}")
    public ResponseEntity<List<RecensioneResponse>> getByProdotto(@PathVariable Long prodottoId) {
        return ResponseEntity.ok(service.ottieniPerProdotto(prodottoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        service.eliminaRecensione(id);
        return ResponseEntity.noContent().build();
    }
}