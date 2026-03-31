package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.ProdottoRequest;
import com.nova.backend.dto.catalogo.ProdottoResponse;
import com.nova.backend.service.catalogo.ProdottoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogo/prodotti")
public class ProdottoController {

    private final ProdottoService service;

    public ProdottoController(ProdottoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdottoResponse> crea(@RequestBody ProdottoRequest request) {
        return ResponseEntity.ok(service.creaProdotto(request));
    }

    @GetMapping
    public ResponseEntity<List<ProdottoResponse>> getAll() {
        return ResponseEntity.ok(service.ottieniTutti());
    }

    @GetMapping("/attivi")
    public ResponseEntity<List<ProdottoResponse>> getActive() {
        return ResponseEntity.ok(service.ottieniAttivi());
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProdottoResponse> getBySku(@PathVariable String sku) {
        return service.ottieniPerSku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}