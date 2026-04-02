package com.nova.backend.controller.ordine;

import com.nova.backend.dto.ordine.richiesta.CartItemRequestDTO;
import com.nova.backend.dto.ordine.risposta.CartResponseDTO;
import com.nova.backend.service.ordine.CarrelloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST per il carrello utente.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/api/v1/carrello", "/api/v1/carts"})
public class CarrelloController {

    private final CarrelloService carrelloService;

    public CarrelloController(CarrelloService carrelloService) {
        this.carrelloService = carrelloService;
    }

    @GetMapping("/{utenteId}")
    public ResponseEntity<CartResponseDTO> ottieniCarrello(@PathVariable Long utenteId) {
        return ResponseEntity.ok(carrelloService.ottieniCarrello(utenteId));
    }

    @PostMapping("/{utenteId}/items")
    public ResponseEntity<CartResponseDTO> aggiungiProdotto(@PathVariable Long utenteId,
                                                            @Valid @RequestBody CartItemRequestDTO request) {
        return ResponseEntity.ok(carrelloService.aggiungiProdotto(utenteId, request));
    }

    @PutMapping("/{utenteId}/items/{prodottoId}")
    public ResponseEntity<CartResponseDTO> aggiornaQuantita(@PathVariable Long utenteId,
                                                            @PathVariable Long prodottoId,
                                                            @RequestParam Integer quantita) {
        return ResponseEntity.ok(carrelloService.aggiornaQuantita(utenteId, prodottoId, quantita));
    }

    @DeleteMapping("/{utenteId}/items/{prodottoId}")
    public ResponseEntity<CartResponseDTO> rimuoviProdotto(@PathVariable Long utenteId,
                                                           @PathVariable Long prodottoId) {
        return ResponseEntity.ok(carrelloService.rimuoviProdotto(utenteId, prodottoId));
    }

    @DeleteMapping("/{utenteId}")
    public ResponseEntity<Void> svuotaCarrello(@PathVariable Long utenteId) {
        carrelloService.svuotaCarrello(utenteId);
        return ResponseEntity.noContent().build();
    }
}
