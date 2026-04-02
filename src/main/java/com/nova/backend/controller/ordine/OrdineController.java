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

    /**
     * Constructs an OrdineController with the required order service and authentication helper.
     */
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
    /**
     * Deletes the order identified by the given id, after validating the user's auth token and client role.
     *
     * @param token   the X-Auth-Token header value used to authenticate the caller
     * @param user_id the X-User-Id header value identifying the caller
     * @param id      the id of the order to delete
     * @return        a ResponseEntity with HTTP 204 No Content if the deletion succeeds; `null` if authentication or role checks fail
     */
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
