package com.nova.backend.service.utente;

import com.nova.backend.dto.RispostaErrore;
import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.request.LoginRequestDTO;
import com.nova.backend.dto.utente.response.UserResponseDTO;
import com.nova.backend.mapper.utente.UtenteMapper;
import com.nova.backend.model.utente.SessioneUtente;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.utente.SessioneUtenteRepository;
import com.nova.backend.repository.utente.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AutenticazioneUtente {

    private final UtenteRepository utenteRepository;
    private final SessioneUtenteRepository sessioneUtenteRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticazioneUtente(UtenteRepository utenteRepository, SessioneUtenteRepository sessioneUtenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.sessioneUtenteRepository = sessioneUtenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // LOGIN
    public Object login(LoginRequestDTO loginRequest) {
        Optional<Utente> userOpt = this.utenteRepository.findByEmail(loginRequest.getEmail());

        if (!userOpt.isPresent()) {
            return new RispostaErrore("Utente non trovato", 404, System.currentTimeMillis());
        }

        Utente utente = userOpt.get();

        if (!utente.isEnabled()) {
            return new RispostaErrore("Utente non attivo", 403, System.currentTimeMillis());
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), utente.getPassword())) {
            return new RispostaErrore("Password errata", 401, System.currentTimeMillis());
        }

        String token = java.util.UUID.randomUUID().toString();

        SessioneUtente sessione = new SessioneUtente();
        sessione.setIdUtente(utente);
        sessione.setToken(token);
        sessione.setCreatedAt(LocalDateTime.now());
        sessione.setExpiresAt(LocalDateTime.now().plusHours(4));
        sessione.setRevoked(false);
        this.sessioneUtenteRepository.save(sessione);

        // Converti l'entità in DTO per evitare di esporre/mutare l'oggetto JPA gestito
        UserResponseDTO utenteDTO = UtenteMapper.toResponse(utente);

        Map<String, Object> payload = new HashMap<>();
        payload.put("utente", utenteDTO);
        payload.put("token", token);
        payload.put("dataScadenza", sessione.getExpiresAt().toString());

        return payload;
    }

    public Object logout(String token, Long idUtente) {
        Optional<SessioneUtente> sessionOpt = this.sessioneUtenteRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            SessioneUtente sessione = sessionOpt.get();
            if (sessione.getIdUtente().getId().equals(idUtente) && !sessione.getRevoked()) {
                sessione.setRevoked(true);
                this.sessioneUtenteRepository.save(sessione);
                return new RispostaGenerica("Logout effettuato con successo", null);
            }
        }
        return new RispostaErrore("Logout fallito", 403, System.currentTimeMillis());
    }

    public boolean isTokenValid(String token, Long idUtente) {
        Optional<Utente> userOpt = this.utenteRepository.findById(idUtente);
        if (!userOpt.isPresent()) {
            return false;
        }
        Optional<SessioneUtente> sessionOpt = this.sessioneUtenteRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            SessioneUtente sessione = sessionOpt.get();
            if (sessione.getIdUtente().getId().equals(idUtente) && !sessione.getRevoked()
                    && sessione.getExpiresAt().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }
}

