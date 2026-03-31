package com.nova.backend.service.utente;

import com.nova.backend.dto.comune.ErrorResponseDTO;
import com.nova.backend.dto.utente.request.LoginRequestDTO;
import com.nova.backend.dto.comune.RispostaGenericaDTO;
import com.nova.backend.model.utente.SessioneUtente;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.SessioneUtenteRepository;
import com.nova.backend.repository.utente.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        if (!userOpt.isPresent())
        {
            return new ErrorResponseDTO("Utente non trovato", 404, System.currentTimeMillis());
        }

        Utente utente = userOpt.get();

        if (utente.isEnabled() == false) {
            return new ErrorResponseDTO("Utente non attivo", 403, System.currentTimeMillis());
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), utente.getPassword())) {
            return new ErrorResponseDTO("Password errata", 401, System.currentTimeMillis());
        }

        String token = java.util.UUID.randomUUID().toString();

        SessioneUtente userSession = new SessioneUtente();
        userSession.setIdUtente(utente);
        userSession.setToken(token);
        userSession.setCreatedAt(LocalDateTime.now());
        userSession.setExpiresAt(LocalDateTime.now().plusHours(4)); // Sessione valida per 8 ore
        userSession.setRevoked(false);
        this.sessioneUtenteRepository.save(userSession);
        Map<String, Object> payload = new java.util.HashMap<>();
        utente.setPassword(null); // Non inviare la password criptata nel payload
        payload.put("utente", utente);
        payload.put("token", token);
        payload.put("expiresAt", userSession.getExpiresAt().toString());


        return payload;
    }

    public Object logout(String token, Long userId)
    {
        Optional<SessioneUtente> sessionOpt = this.sessioneUtenteRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            SessioneUtente session = sessionOpt.get();
            if (session.getIdUtente().getId().equals(userId) && !session.getRevoked()) {
                session.setRevoked(true);
                this.sessioneUtenteRepository.save(session);
                return new RispostaGenericaDTO("logout effettuato con successo", null);
            }
        }
        return new ErrorResponseDTO("logout fallito", 403, System.currentTimeMillis());
    }

    public boolean isTokenValid(String token, Long userId) {
        Optional<Utente> userOpt = this.utenteRepository.findById(userId);
        if (!userOpt.isPresent())
        {
            return false;
        }
        Optional<SessioneUtente> sessionOpt = this.sessioneUtenteRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            SessioneUtente session = sessionOpt.get();
            if (session.getIdUtente().getId().equals(userId) && !session.getRevoked()
                    && session.getExpiresAt().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public Object checkAuthError(String token, Long user_id) {
        if (token==null || user_id==null) {
            return new ErrorResponseDTO("Token o user_id mancanti", 400, System.currentTimeMillis());
        }
        boolean valid = this.isTokenValid(token, user_id);
        if (!valid) {
            return new ErrorResponseDTO("Token non valido", 401, System.currentTimeMillis());
        }

        return null;
    }

}
