package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.CategoriaDTO;
import com.nova.backend.service.catalogo.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/api/v1/catalogo/categoria", "/api/v1/catalogo/categorie"})
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaDTO> creaCategoria(@Valid @RequestBody CategoriaDTO request) {
        return new ResponseEntity<>(service.creaCategoria(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> ottieniTutte() {
        return ResponseEntity.ok(service.ottieniTutte());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> ottieniPerId(@PathVariable Long id) {
        return ResponseEntity.ok(service.ottieniPerId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoriaDTO> aggiornaCategoria(@PathVariable Long id, @RequestBody CategoriaDTO request) {
        return ResponseEntity.ok(service.aggiornaCategoria(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminaCategoria(@PathVariable Long id) {
        service.eliminaCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
