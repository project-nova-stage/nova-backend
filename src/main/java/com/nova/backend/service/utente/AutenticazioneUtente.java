package com.nova.backend.service.utente;

import com.nova.backend.dto.RispostaErrore;
import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.richiesta.LoginRequestDTO;
import com.nova.backend.dto.utente.risposta.UserResponseDTO;
import com.nova.backend.exception.EccezioneApplicativa;
import com.nova.backend.mapper.utente.UtenteMapper;
import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.SessioneUtente;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.utente.SessioneUtenteRepository;
import com.nova.backend.repository.utente.UtenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

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
    public Map<String, Object> login(LoginRequestDTO loginRequest) {
        Utente utente = this.utenteRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new EccezioneApplicativa("Utente non trovato", HttpStatus.NOT_FOUND));

        if (!utente.isEnabled()) {
            throw new EccezioneApplicativa("Utente non attivo", HttpStatus.FORBIDDEN);
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), utente.getPassword())) {
            throw new EccezioneApplicativa("Password errata", HttpStatus.UNAUTHORIZED);
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

    public RispostaGenerica logout(String token, Long idUtente) {
        Optional<SessioneUtente> sessionOpt = this.sessioneUtenteRepository.findByToken(token);
        if (sessionOpt.isPresent()) {
            SessioneUtente sessione = sessionOpt.get();
            if (sessione.getIdUtente().getId().equals(idUtente) && !sessione.getRevoked()) {
                sessione.setRevoked(true);
                this.sessioneUtenteRepository.save(sessione);
                return new RispostaGenerica("Logout effettuato con successo", null);
            }
        }
        throw new EccezioneApplicativa("Token non valido o sessione non trovata", HttpStatus.UNAUTHORIZED);
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


    public Object checkAuthError(String token, Long user_id) {
        if (token == null || user_id == null) {
            return new RispostaErrore("Token o user_id mancanti", 400, System.currentTimeMillis());
        }
        boolean valid = this.isTokenValid(token, user_id);
        if (!valid) {
            return new RispostaErrore("Token non valido", 401, System.currentTimeMillis());
        }

        return null;
    }


    //VERIFICA SE è UN ADMIN
    public Object checkAdminError(Long user_id) {
        Optional<Utente> userOpt = this.utenteRepository.findById(user_id);
        if (!userOpt.isPresent()) {
            return new RispostaErrore("User non trovato", 400, System.currentTimeMillis());
        }
        Ruolo ruolo = userOpt.get().getRuolo();
        if(ruolo.equals(Ruolo.ADMIN)) {
            System.out.println("55");
            return null;
        }else{
            System.out.println("60");
            return new RispostaErrore("Non autorizato", 404, System.currentTimeMillis());
        }
    }

    //VERIFICA SE è UN CLIENTE
    public Object checkClienteError(Long user_id) {
        Optional<Utente> userOpt = this.utenteRepository.findById(user_id);
        if (!userOpt.isPresent()) {
            return new RispostaErrore("User non trovato", 400, System.currentTimeMillis());
        }
        Ruolo ruolo = userOpt.get().getRuolo();
        if(ruolo.equals(Ruolo.CLIENTE)) {
            System.out.println("55");
            return null;
        }else{
            System.out.println("60");
            return new RispostaErrore("Non autorizato", 404, System.currentTimeMillis());
        }
    }


}

