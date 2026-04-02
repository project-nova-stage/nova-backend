package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.richiesta.ProdottoRequestDTO;
import com.nova.backend.dto.catalogo.risposta.ProdottoResponseDTO;
import com.nova.backend.service.catalogo.ProdottoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/catalogo/prodotto", "/api/v1/catalogo/prodotti"})
public class ProdottoController {

    private final ProdottoService service;

    public ProdottoController(ProdottoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdottoResponseDTO> creaProdotto(@Valid @RequestBody ProdottoRequestDTO request) {
        return new ResponseEntity<>(service.creaProdotto(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdottoResponseDTO>> listaProdotti() {
        return ResponseEntity.ok(service.ottieniProdottiAttivi());
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProdottoResponseDTO> ottieniPerSku(@PathVariable String sku) {
        return ResponseEntity.ok(service.ottieniPerSku(sku));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponseDTO> ottieniPerId(@PathVariable Long id) {
        return ResponseEntity.ok(service.ottieniPerId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdottoResponseDTO> aggiornaProdotto(@PathVariable Long id, @RequestBody ProdottoRequestDTO request) {
        return ResponseEntity.ok(service.aggiornaProdotto(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminaProdotto(@PathVariable Long id) {
        service.eliminaProdotto(id);
        return ResponseEntity.noContent().build();
    }
}
