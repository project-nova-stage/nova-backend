package com.nova.backend.controller;

import com.nova.backend.DTO.RequestRegistrazione;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.service.utente.ServiceUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utente")
public class UtenteController {

    //@Autowired
    private final ServiceUtente serviceUtente;

    public UtenteController(ServiceUtente serviceUtente) {
        this.serviceUtente = serviceUtente;
    }

    @PostMapping("/registrazione")
    public Object registerUser(@RequestBody RequestRegistrazione req)
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

}
