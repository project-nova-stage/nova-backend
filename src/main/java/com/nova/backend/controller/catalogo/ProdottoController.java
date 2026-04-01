package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.catalogo.richiesta.ProdottoRequestDTO;
import com.nova.backend.dto.catalogo.risposta.ProdottoResponseDTO;
import com.nova.backend.service.catalogo.ProdottoService;
import com.nova.backend.service.utente.AutenticazioneUtente;
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
    private final AutenticazioneUtente autenticazioneUtente;

    public ProdottoController(ProdottoService service, AutenticazioneUtente autenticazioneUtente) {
        this.service = service;
        this.autenticazioneUtente = autenticazioneUtente;
    }

    /**
     * Crea un nuovo prodotto.
     */
    @PostMapping
    public ResponseEntity<ProdottoResponseDTO> creaProdotto(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @Valid @RequestBody ProdottoRequestDTO request) {
        //Verifica se il token dell'utente è valido o meno
        Object err = this.autenticazioneUtente.checkAuthError(token, user_id);
        ProdottoResponseDTO response;
        if (err == null) {
            response = service.creaProdotto(request);
        } else {
            return null;
        }
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
    public ResponseEntity<ProdottoResponseDTO> aggiornaProdotto(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @PathVariable Long id, @RequestBody ProdottoRequestDTO request) {
        Object err = this.autenticazioneUtente.checkAuthError(token, user_id);
        if (err == null) {
            return ResponseEntity.ok(service.aggiornaProdotto(id, request));
        }else{
            return null;
        }

    }

    /**
     * Elimina un prodotto.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaProdotto(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @PathVariable Long id) {
        Object err = this.autenticazioneUtente.checkAuthError(token, user_id);
        if (err == null) {
            service.eliminaProdotto(id);
        }else {
            return null;
        }

        return ResponseEntity.noContent().build();
    }
}