package com.nova.backend.service.utente;

import com.nova.backend.dto.comune.ErrorResponseDTO;
import com.nova.backend.dto.comune.RispostaGenericaDTO;
import com.nova.backend.dto.utente.RequestRegistrazioneDTO;
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
    public Object registrazioneUtente(RequestRegistrazioneDTO req) {
        // VERIFICA SE L'UTENTE ESISTE GIA TRAMITE EMAIL
        if (this.utenteRepository.existsByEmail(req.getEmail())) {
            return new ErrorResponseDTO("Email gia in uso", 401, System.currentTimeMillis());
        }

        // INSERIMENTO DEI DATI
        Utente utente = new Utente();
        utente.setEmail(req.getEmail());
        utente.setNome(req.getNome());
        utente.setCognome(req.getCognome());

        // VERIFICHE RUOLO E TIPO CLIENTE
        if (ruoloValido(req.getRuolo())) {
            utente.setRuolo(Ruolo.valueOf(req.getRuolo()));
        } else {
            return new ErrorResponseDTO("Ruolo inesistente", 401, System.currentTimeMillis());
        }

        if (tipoValido(req.getTipoCliente())) {
            utente.setTipoCliente(TipoCliente.valueOf(req.getTipoCliente()));
        } else {
            return new ErrorResponseDTO("Tipo inesistente", 401, System.currentTimeMillis());
        }

        utente.setPassword(passwordEncoder.encode(req.getPassword()));
        utente.setAttivo(true);
        Utente utenteSalvato = utenteRepository.save(utente);

        return new RispostaGenericaDTO("Utente registrato correttamente", utenteSalvato);
    }

    public List<Utente> findAllUsers() {
        return this.utenteRepository.findAll();
    }

    // VERIFICA SE QUELLO CHE VIENE INSERITO CORRISPONDE ALL'ENUM
    public boolean ruoloValido(Object ruolo) {
        if (ruolo == null) return false;
        try {
            Ruolo.valueOf(ruolo.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean tipoValido(Object tipo) {
        if (tipo == null) return false;
        try {
            TipoCliente.valueOf(tipo.toString());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
