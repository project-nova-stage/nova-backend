package com.nova.backend.service.utente;

import com.nova.backend.dto.RispostaErrore;
import com.nova.backend.dto.RispostaGenerica;
import com.nova.backend.dto.utente.richiesta.LoginRequestDTO;
import com.nova.backend.dto.utente.risposta.UserResponseDTO;
import com.nova.backend.exception.EccezioneApplicativa;
import com.nova.backend.mapper.utente.UtenteMapper;
import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.utente.UtenteRepository;
import com.nova.backend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AutenticazioneUtente {

    private final UtenteRepository utenteRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AutenticazioneUtente(UtenteRepository utenteRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.utenteRepository = utenteRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // LOGIN
    public Map<String, Object> login(LoginRequestDTO loginRequest) {
        org.springframework.security.core.Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new EccezioneApplicativa("Credenziali errate", HttpStatus.UNAUTHORIZED);
        }

        Utente utente = (Utente) authentication.getPrincipal();

        if (!utente.isEnabled()) {
            throw new EccezioneApplicativa("Utente non attivo", HttpStatus.FORBIDDEN);
        }

        String jwtToken = jwtService.generateToken(utente);

        UserResponseDTO utenteDTO = UtenteMapper.toResponse(utente);

        Map<String, Object> payload = new HashMap<>();
        payload.put("utente", utenteDTO);
        payload.put("token", jwtToken);

        return payload;
    }

    public RispostaGenerica logout(String token, Long idUtente) {
        // Con JWT stateless non invalidiamo il token su DB, al limite mettiamo in blacklist
        // Per ora simuliamo un success, dato che il frontend distruggerà il token.
        return new RispostaGenerica("Logout effettuato con successo", null);
    }


    public Object checkAuthError(String token, Long user_id) {
        if (token == null || user_id == null) {
            return new RispostaErrore("Token o user_id mancanti", 400, System.currentTimeMillis());
        }
        
        Optional<Utente> userOpt = utenteRepository.findById(user_id);
        if (userOpt.isEmpty()) {
            return new RispostaErrore("Utente non trovato", 404, System.currentTimeMillis());
        }
        
        boolean valid = jwtService.isTokenValid(token.replace("Bearer ", ""), userOpt.get());
        if (!valid) {
            return new RispostaErrore("Token non valido", 401, System.currentTimeMillis());
        }

        return null;
    }


    /**
     * Verifies whether the user identified by the given id has the ADMIN role.
     *
     * @param user_id the id of the user to check
     * @return `null` if the user exists and has role `Ruolo.ADMIN`; a `RispostaErrore` with message "User non trovato" and status 400 if the user does not exist; a `RispostaErrore` with message "Non autorizzato" and status 404 if the user exists but does not have the ADMIN role
     */
    public Object checkAdminError(Long user_id) {
        Optional<Utente> userOpt = this.utenteRepository.findById(user_id);
        if (!userOpt.isPresent()) {
            return new RispostaErrore("User non trovato", 400, System.currentTimeMillis());
        }
        Ruolo ruolo = userOpt.get().getRuolo();
        if(ruolo.equals(Ruolo.ADMIN)) {
            return null;
        }else{
            return new RispostaErrore("Non autorizzato", 403, System.currentTimeMillis());
        }
    }

    //VERIFICA SE è UN CLIENTE
    public Object checkClienteError(Long user_id) {
        Optional<Utente> userOpt = this.utenteRepository.findById(user_id);
        if (userOpt.isEmpty()) {
            return new RispostaErrore("User non trovato", 400, System.currentTimeMillis());
        }
        Ruolo ruolo = userOpt.get().getRuolo();
        if (ruolo.equals(Ruolo.CLIENTE)) {
            return null;
        } else {
            return new RispostaErrore("Non autorizzato", 403, System.currentTimeMillis());
        }
    }

    


}

