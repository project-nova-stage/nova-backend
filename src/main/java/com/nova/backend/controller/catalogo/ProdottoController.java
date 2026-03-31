package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.ProdottoRequestDTO;
import com.nova.backend.dto.catalogo.ProdottoResponseDTO;
import com.nova.backend.service.catalogo.ProdottoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione dei prodotti nel catalogo.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/catalogo/prodotto")
public class ProdottoController {

    private final ProdottoService service;

    public ProdottoController(ProdottoService service) {
        this.service = service;
    }

    /**
     * Crea un nuovo prodotto.
     */
    @PostMapping
    public ResponseEntity<ProdottoResponseDTO> creaProdotto(@Valid @RequestBody ProdottoRequestDTO request) {
        ProdottoResponseDTO response = service.creaProdotto(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Lista di tutti i prodotti attivi.
     */
    @GetMapping
    public ResponseEntity<List<ProdottoResponseDTO>> listaProdotti() {
        return ResponseEntity.ok(service.ottieniProdottiAttivi());
    }

    /**
     * Recupera un prodotto per SKU.
     */
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProdottoResponseDTO> ottieniPerSku(@PathVariable String sku) {
        return ResponseEntity.ok(service.ottieniPerSku(sku));
    }

    /**
     * Recupera un prodotto per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> ottieniPerId(@PathVariable Long id) {
        return ResponseEntity.ok(service.ottieniPerId(id));
    }

    /**
     * Aggiorna un prodotto.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> aggiornaProdotto(@PathVariable Long id, @RequestBody ProdottoRequestDTO request) {
        return ResponseEntity.ok(service.aggiornaProdotto(id, request));
    }

    /**
     * Elimina un prodotto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
        service.eliminaProdotto(id);
        return ResponseEntity.noContent().build();
    }
}