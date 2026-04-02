package com.nova.backend.service.utente;

import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.richiesta.AggiornaProfiloDTO;
import com.nova.backend.dto.utente.richiesta.RegistroUtenteDTO;
import com.nova.backend.dto.utente.risposta.UserResponseDTO;
import com.nova.backend.exception.EccezioneApplicativa;
import com.nova.backend.mapper.utente.UtenteMapper;
import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.TipoCliente;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.utente.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public RispostaGenerica registrazioneUtente(RegistroUtenteDTO req) {
        // Verifica se l'utente esiste già tramite email
        if (this.utenteRepository.existsByEmail(req.getEmail())) {
            throw new EccezioneApplicativa("Email già in uso", HttpStatus.CONFLICT);
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
                throw new EccezioneApplicativa("Ruolo inesistente", HttpStatus.BAD_REQUEST);
            }
        } else {
            utente.setRuolo(Ruolo.CLIENTE);
        }

        // TipoCliente: applicabile solo al ruolo CLIENTE
        if (req.getTipoCliente() != null && !req.getTipoCliente().isBlank()) {
            if (utente.getRuolo() != Ruolo.CLIENTE) {
                throw new EccezioneApplicativa(
                        "Il tipo cliente non è applicabile per il ruolo " + utente.getRuolo().name(),
                        HttpStatus.BAD_REQUEST);
            }
            if (tipoValido(req.getTipoCliente())) {
                utente.setTipoCliente(TipoCliente.valueOf(req.getTipoCliente()));
            } else {
                throw new EccezioneApplicativa("Tipo cliente inesistente", HttpStatus.BAD_REQUEST);
            }
        }

        utente.setPassword(passwordEncoder.encode(req.getPassword()));
        utente.setAttivo(true);
        Utente utenteSalvato = utenteRepository.save(utente);

        return new RispostaGenerica("Utente registrato correttamente", UtenteMapper.toResponse(utenteSalvato));
    }

    public List<UserResponseDTO> findAllUsers() {
        return this.utenteRepository.findAll().stream()
                .map(UtenteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getProfiloUtente(Long idUtente) {
        Utente utente = this.utenteRepository.findById(idUtente)
                .orElseThrow(() -> new EccezioneApplicativa("Utente non trovato", HttpStatus.NOT_FOUND));
        return UtenteMapper.toResponse(utente);
    }

    public UserResponseDTO aggiornaProfiloUtente(Long idUtente, AggiornaProfiloDTO req) {
        Utente utente = this.utenteRepository.findById(idUtente)
                .orElseThrow(() -> new EccezioneApplicativa("Utente non trovato", HttpStatus.NOT_FOUND));

        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            this.utenteRepository.findByEmail(req.getEmail())
                    .filter(trovato -> !trovato.getId().equals(idUtente))
                    .ifPresent(trovato -> {
                        throw new EccezioneApplicativa("Email già in uso", HttpStatus.CONFLICT);
                    });
            utente.setEmail(req.getEmail());
        }

        if (req.getNome() != null && !req.getNome().isBlank()) {
            utente.setNome(req.getNome());
        }

        if (req.getCognome() != null && !req.getCognome().isBlank()) {
            utente.setCognome(req.getCognome());
        }

        if (req.getTipoCliente() != null && !req.getTipoCliente().isBlank()) {
            if (utente.getRuolo() != Ruolo.CLIENTE) {
                throw new EccezioneApplicativa(
                        "Il tipo cliente non è applicabile per il ruolo " + utente.getRuolo().name(),
                        HttpStatus.BAD_REQUEST);
            }
            if (!tipoValido(req.getTipoCliente())) {
                throw new EccezioneApplicativa("Tipo cliente inesistente", HttpStatus.BAD_REQUEST);
            }
            utente.setTipoCliente(TipoCliente.valueOf(req.getTipoCliente()));
        }

        Utente aggiornato = this.utenteRepository.save(utente);
        return UtenteMapper.toResponse(aggiornato);
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

