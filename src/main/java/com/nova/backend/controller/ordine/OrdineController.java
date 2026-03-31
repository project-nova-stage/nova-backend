package com.nova.backend.controller.ordine;

import com.nova.backend.dto.ordine.OrdineDTO;
import com.nova.backend.model.ordine.StatoOrdine;
import com.nova.backend.service.ordine.OrdineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordini")
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping
    public ResponseEntity<OrdineDTO> creaOrdine(@RequestBody OrdineDTO ordineDTO) {
        OrdineDTO creato = ordineService.creaOrdine(ordineDTO);
        return ResponseEntity.ok(creato);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdineDTO> getOrdineById(@PathVariable Long id) {
        OrdineDTO ordine = ordineService.getOrdineById(id);
        return ResponseEntity.ok(ordine);
    }

    @GetMapping
    public ResponseEntity<List<OrdineDTO>> listaOrdini(
            @RequestParam(required = false) Long utenteId,
            @RequestParam(required = false) StatoOrdine stato) {

        if (utenteId != null) {
            return ResponseEntity.ok(ordineService.getOrdiniByUtenteId(utenteId));
        }

        if (stato != null) {
            return ResponseEntity.ok(ordineService.getOrdiniByStato(stato));
        }

        return ResponseEntity.ok(ordineService.getAllOrdini());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdineDTO> aggiornaOrdine(@PathVariable Long id, @RequestBody OrdineDTO ordineDTO) {
        OrdineDTO aggiornato = ordineService.aggiornaOrdine(id, ordineDTO);
        return ResponseEntity.ok(aggiornato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaOrdine(@PathVariable Long id) {
        ordineService.eliminaOrdine(id);
        return ResponseEntity.noContent().build();
    }
}
