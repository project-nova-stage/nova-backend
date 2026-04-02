package com.nova.backend.controller.ordine;

import com.nova.backend.dto.RispostaErrore;
import com.nova.backend.dto.ordine.OrdineDTO;
import com.nova.backend.model.ordine.StatoOrdine;
import com.nova.backend.service.ordine.OrdineService;
import com.nova.backend.service.utente.AutenticazioneUtente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordini")
public class OrdineController {

    private final OrdineService ordineService;
    private final AutenticazioneUtente autenticazioneUtente;

    public OrdineController(OrdineService ordineService, AutenticazioneUtente autenticazioneUtente) {
        this.ordineService = ordineService;
        this.autenticazioneUtente = autenticazioneUtente;
    }

    @PostMapping
    public ResponseEntity<?> creaOrdine(
            @RequestHeader("Authorization") String token,
            @RequestParam Long user_id,
            @RequestBody OrdineDTO ordineDTO) {
        
        Object authError = autenticazioneUtente.checkAuthError(token, user_id);
        if (authError instanceof RispostaErrore) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authError);
        }

        Object roleError = autenticazioneUtente.checkClienteError(user_id);
        if (roleError instanceof RispostaErrore) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(roleError);
        }

        if (!ordineDTO.getUtenteId().equals(user_id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new RispostaErrore("Non autorizzato ad agire per un altro utente", 403, System.currentTimeMillis()));
        }

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
    public ResponseEntity<?> aggiornaOrdine(
            @RequestHeader("Authorization") String token,
            @RequestParam Long user_id,
            @PathVariable Long id, 
            @RequestBody OrdineDTO ordineDTO) {

        Object authError = autenticazioneUtente.checkAuthError(token, user_id);
        if (authError instanceof RispostaErrore) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authError);
        }

        Object roleError = autenticazioneUtente.checkClienteError(user_id);
        if (roleError instanceof RispostaErrore) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(roleError);
        }

        Object ownershipError = ordineService.verificaAppartenenza(user_id, id);
        if (ownershipError instanceof RispostaErrore) {
            RispostaErrore err = (RispostaErrore) ownershipError;
            return ResponseEntity.status(err.getCodice()).body(err);
        }

        OrdineDTO aggiornato = ordineService.aggiornaOrdine(id, ordineDTO);
        return ResponseEntity.ok(aggiornato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminaOrdine(
            @RequestHeader("Authorization") String token,
            @RequestParam Long user_id,
            @PathVariable Long id) {

        Object authError = autenticazioneUtente.checkAuthError(token, user_id);
        if (authError instanceof RispostaErrore) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authError);
        }

        Object roleError = autenticazioneUtente.checkClienteError(user_id);
        if (roleError instanceof RispostaErrore) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(roleError);
        }

        Object ownershipError = ordineService.verificaAppartenenza(user_id, id);
        if (ownershipError instanceof RispostaErrore) {
            RispostaErrore err = (RispostaErrore) ownershipError;
            return ResponseEntity.status(err.getCodice()).body(err);
        }

        ordineService.eliminaOrdine(id);
        return ResponseEntity.noContent().build();
    }
}
