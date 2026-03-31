package com.nova.backend.service.utente;

import com.nova.backend.dto.RispostaErrore;
import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.request.RegistroUtenteDTO;
import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.TipoCliente;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.utente.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ServiceUtente {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public ServiceUtente(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // REGISTRAZIONE UTENTE
    public Object registrazioneUtente(RegistroUtenteDTO req) {
        // Verifica se l'utente esiste già tramite email
        if (this.utenteRepository.existsByEmail(req.getEmail())) {
            return new RispostaErrore("Email già in uso", 409, System.currentTimeMillis());
        }

        Utente utente = new Utente();
        utente.setEmail(req.getEmail());
        utente.setNome(req.getNome());
        utente.setCognome(req.getCognome());

        // Ruolo: default CLIENTE se non specificato
        if (req.getCodiceRuolo() != null && !req.getCodiceRuolo().isBlank()) {
            if (ruoloValido(req.getCodiceRuolo())) {
                utente.setRuolo(Ruolo.valueOf(req.getCodiceRuolo()));
            } else {
                return new RispostaErrore("Ruolo inesistente", 400, System.currentTimeMillis());
            }
        } else {
            utente.setRuolo(Ruolo.CLIENTE);
        }

        // TipoCliente: applicabile solo al ruolo CLIENTE
        if (req.getTipoCliente() != null && !req.getTipoCliente().isBlank()) {
            if (utente.getRuolo() != Ruolo.CLIENTE) {
                return new RispostaErrore("Il tipo cliente non è applicabile per il ruolo " + utente.getRuolo().name(), 400, System.currentTimeMillis());
            }
            if (tipoValido(req.getTipoCliente())) {
                utente.setTipoCliente(TipoCliente.valueOf(req.getTipoCliente()));
            } else {
                return new RispostaErrore("Tipo cliente inesistente", 400, System.currentTimeMillis());
            }
        }

        utente.setPassword(passwordEncoder.encode(req.getPassword()));
        utente.setAttivo(true);
        Utente utenteSalvato = utenteRepository.save(utente);

        return new RispostaGenerica("Utente registrato correttamente", utenteSalvato);
    }

    public List<Utente> findAllUsers() {
        return this.utenteRepository.findAll();
    }

    // Verifica se quello che viene inserito corrisponde all'enum
    public boolean ruoloValido(Object ruolo) {
        try {
            Ruolo.valueOf(ruolo.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean tipoValido(Object tipo) {
        try {
            TipoCliente.valueOf(tipo.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
