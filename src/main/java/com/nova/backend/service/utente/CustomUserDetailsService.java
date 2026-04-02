package com.nova.backend.service.utente;

import com.nova.backend.repository.utente.UtenteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtenteRepository utenteRepository;

    public CustomUserDetailsService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenteRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con email: " + username));
    }
}
