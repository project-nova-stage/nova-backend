package com.nova.backend.controller.catalogo;

import com.nova.backend.dto.catalogo.CategoriaDTO;
import com.nova.backend.service.catalogo.CategoriaService;
import com.nova.backend.service.utente.AutenticazioneUtente;
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
    private final AutenticazioneUtente autenticazioneUtente;

    public CategoriaController(CategoriaService service, AutenticazioneUtente autenticazioneUtente) {
        this.service = service;
        this.autenticazioneUtente = autenticazioneUtente;
    }

    /**
     * Crea una nuova categoria.
     */
    @PostMapping
    public ResponseEntity<CategoriaDTO> creaCategoria(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @Valid @RequestBody CategoriaDTO request) {
        //Verifica se il token dell'utente è valido o meno
        Object authErr = this.autenticazioneUtente.checkAuthError(token, user_id);
        CategoriaDTO response;
        if (authErr == null) {
            //Verifica se l'utente è un admin
            Object authCheck = this.autenticazioneUtente.checkAdminError(user_id);
            if (authCheck == null) {
                response = service.creaCategoria(request);
                } else {
                    return null;
                }
             }else{
                return null;
            }
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
    public ResponseEntity<CategoriaDTO> aggiornaCategoria(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @PathVariable Long id, @RequestBody CategoriaDTO request) {
        //Verifica se il token dell'utente è valido o meno
        Object authErr = this.autenticazioneUtente.checkAuthError(token, user_id);
        if(authErr == null) {
            //Verifica se l'utente è un admin
            Object authCheck = this.autenticazioneUtente.checkAdminError(user_id);
            if (authCheck == null) {
                return ResponseEntity.ok(service.aggiornaCategoria(id, request));
            } else {
                return null;
            }
        }else {
            return null;
        }

    }

    /**
     * Elimina una categoria.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaCategoria(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long user_id, @PathVariable Long id) {
        //Verifica se il token dell'utente è valido o meno
        Object authErr = this.autenticazioneUtente.checkAuthError(token, user_id);
        if(authErr == null){
            //Verifica se l'utente è un admin
            Object authCheck = this.autenticazioneUtente.checkAdminError(user_id);
            if (authCheck == null) {
                service.eliminaCategoria(id);
            } else {
                return null;
            }
        }else {
            return null;
        }
        return ResponseEntity.noContent().build();
    }
}