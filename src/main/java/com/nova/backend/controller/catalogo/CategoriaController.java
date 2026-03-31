package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.CategoriaRequest;
import com.nova.backend.dto.catalogo.CategoriaResponse;
import com.nova.backend.service.catalogo.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogo/categorie")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> crea(@RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(service.creaCategoria(request));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAll() {
        return ResponseEntity.ok(service.ottieniTutte());
    }

    @GetMapping("/root")
    public ResponseEntity<List<CategoriaResponse>> getRootCategories() {
        return ResponseEntity.ok(service.ottieniCategoriePrincipali());
    }
}