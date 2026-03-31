package com.nova.backend.repository;

import com.nova.backend.model.utente.SessioneUtente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessioneUtenteRepository extends JpaRepository<SessioneUtente,Long> {
    Optional<SessioneUtente> findByToken(String token);
}
