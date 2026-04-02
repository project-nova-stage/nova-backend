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
    /**
     * Create a new order for the authenticated user.
     *
     * @param token     the X-Auth-Token header value used for authentication
     * @param user_id   the X-User-Id header value identifying the requesting user
     * @param ordineDTO the order data to create
     * @return a ResponseEntity containing the created OrdineDTO, or `null` if authentication or role checks fail
     */
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

    /**
     * Fetches an order by its identifier.
     *
     * @param id the identifier of the order to retrieve
     * @return a ResponseEntity containing the requested OrdineDTO with HTTP 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdineDTO> getOrdineById(@PathVariable Long id) {
        OrdineDTO ordine = ordineService.getOrdineById(id);
        return ResponseEntity.ok(ordine);
    }

    /**
     * Retrieves orders, optionally filtered by user or status.
     *
     * If `utenteId` is provided, returns orders for that user. Otherwise, if `stato` is provided,
     * returns orders with that status. If neither is provided, returns all orders.
     *
     * @param utenteId optional user ID to filter orders by user
     * @param stato optional order status to filter orders by status
     * @return HTTP 200 with a list of matching {@code OrdineDTO} objects
     */
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
    /**
     * Updates the order identified by the given id with the provided data.
     *
     * @param token    the 'X-Auth-Token' header value used to authenticate the requester
     * @param user_id  the 'X-User-Id' header value identifying the requester
     * @param id       the id of the order to update
     * @param ordineDTO the order data to apply to the existing order
     * @return the updated OrdineDTO wrapped in a 200 OK response, or `null` if authentication or role checks fail
     */
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
            return ResponseEntity.status(err.getStato()).body(err);
        }

        OrdineDTO aggiornato = ordineService.aggiornaOrdine(id, ordineDTO);
        return ResponseEntity.ok(aggiornato);
    }
    /**
     * Deletes the order identified by the given id, after validating the user's auth token and client role.
     *
     * @param token   the X-Auth-Token header value used to authenticate the caller
     * @param user_id the X-User-Id header value identifying the caller
     * @param id      the id of the order to delete
     * @return        a ResponseEntity with HTTP 204 No Content if the deletion succeeds; `null` if authentication or role checks fail
     */
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
            return ResponseEntity.status(err.getStato()).body(err);
        }

        ordineService.eliminaOrdine(id);
        return ResponseEntity.noContent().build();
    }
}
