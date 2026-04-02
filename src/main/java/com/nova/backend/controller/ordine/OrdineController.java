package com.nova.backend.controller.ordine;

import com.nova.backend.dto.ordine.OrdineDTO;
import com.nova.backend.model.ordine.StatoOrdine;
import com.nova.backend.service.ordine.OrdineService;
import com.nova.backend.service.utente.AutenticazioneUtente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ordini")
public class OrdineController {

    private final OrdineService ordineService;
    private final AutenticazioneUtente  autenticazioneUtente;

    public OrdineController(OrdineService ordineService, AutenticazioneUtente autenticazioneUtente) {
        this.ordineService = ordineService;
        this.autenticazioneUtente = autenticazioneUtente;
    }
    //f
    @PostMapping
    public ResponseEntity<OrdineDTO> creaOrdine(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @RequestBody OrdineDTO ordineDTO) {
        //Verifica se il token dell'utente è valido o meno
        Object authErr = this.autenticazioneUtente.checkAuthError(token, user_id);
        if(authErr == null) {
            //Verifica se l'utente è un cliente
            Object authCheck = this.autenticazioneUtente.checkClienteError(user_id);
            if(authCheck == null) {
                OrdineDTO creato = ordineService.creaOrdine(ordineDTO);
                return ResponseEntity.ok(creato);
            }else{
                return null;
            }
        }else{
            return null;
        }

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
    //f
    @PutMapping("/{id}")
    public ResponseEntity<OrdineDTO> aggiornaOrdine(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @PathVariable Long id, @RequestBody OrdineDTO ordineDTO) {
        //Verifica se il token dell'utente è valido o meno
        Object authErr = this.autenticazioneUtente.checkAuthError(token, user_id);
        if(authErr == null) {
            //Verifica se l'utente è un cliente
            Object authCheck = this.autenticazioneUtente.checkClienteError(user_id);
            if(authCheck == null) {
                OrdineDTO aggiornato = ordineService.aggiornaOrdine(id, ordineDTO);
                return ResponseEntity.ok(aggiornato);
            }else{
                return null;
            }
        }else{
            return null;
        }

    }
    //f
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaOrdine(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @PathVariable Long id) {
        //Verifica se il token dell'utente è valido o meno
        Object authErr = this.autenticazioneUtente.checkAuthError(token, user_id);
        if(authErr == null) {
            //Verifica se l'utente è un cliente
            Object authCheck = this.autenticazioneUtente.checkClienteError(user_id);
            if(authCheck == null) {
                ordineService.eliminaOrdine(id);
            }else{
                return null;
            }
        }else{
            return null;
        }
        return ResponseEntity.noContent().build();
    }
}
