package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.CategoriaDTO;
import com.nova.backend.service.catalogo.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller per la gestione delle categorie dei prodotti.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/catalogo/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    /**
     * Crea una nuova categoria.
     */
    @PostMapping
    public ResponseEntity<CategoriaDTO> creaCategoria(@Valid @RequestBody CategoriaDTO request) {
        CategoriaDTO response = service.creaCategoria(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Ottiene tutte le categorie.
     */
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> ottieniTutte() {
        return ResponseEntity.ok(service.ottieniTutte());
    }

    /**
     * Ottiene una categoria per ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> ottieniPerId(@PathVariable Long id) {
        return ResponseEntity.ok(service.ottieniPerId(id));
    }

    /**
     * Aggiorna una categoria esistente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> aggiornaCategoria(@PathVariable Long id, @RequestBody CategoriaDTO request) {
        return ResponseEntity.ok(service.aggiornaCategoria(id, request));
    }

    /**
     * Elimina una categoria.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaCategoria(@PathVariable Long id) {
        service.eliminaCategoria(id);
        return ResponseEntity.noContent().build();
    }
}