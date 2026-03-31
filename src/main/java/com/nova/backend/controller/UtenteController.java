package com.nova.backend.controller;

import com.nova.backend.dto.utente.request.LoginRequestDTO;
import com.nova.backend.dto.utente.RequestRegistrazioneDTO;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.service.utente.AutenticazioneUtente;
import com.nova.backend.service.utente.ServiceUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utente")
public class UtenteController {

    //@Autowired
    private final ServiceUtente serviceUtente;
    private final AutenticazioneUtente autenticazioneUtente;

    public UtenteController(ServiceUtente serviceUtente, AutenticazioneUtente autenticazioneUtente) {
        this.serviceUtente = serviceUtente;
        this.autenticazioneUtente = autenticazioneUtente;
    }

    @PostMapping("/registrazione")
    public Object registerUser(@RequestBody RequestRegistrazioneDTO req)
    {
        System.out.println("Sto registrando un utente");
        System.out.println("Registering utente: " + req.getNome() + " " + req.getCognome());
        return this.serviceUtente.registrazioneUtente(req);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Utente>> getAllUsers() {
        List<Utente> users = serviceUtente.findAllUsers();

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequestDTO req) {
        System.out.println("Sto effettuando il login");
        return this.autenticazioneUtente.login(req);
    }

    @PostMapping("/logout")
    public Object logout(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long idUtente) {
        System.out.println("Sto effettuando il logout");
        return this.autenticazioneUtente.logout(token, idUtente);
    }


    @PostMapping("/validazioneToken")
    public Object validateToken(@RequestHeader("X-Auth-Token") String token, @RequestHeader("X-User-Id") Long idUtente) {
        HashMap<String, Object> response = new HashMap<>();
        boolean isValid = this.autenticazioneUtente.isTokenValid(token, idUtente);
        response.put("isValid", isValid);
        return response;
    }

}
