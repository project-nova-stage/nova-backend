package com.nova.backend.repository;

import com.nova.backend.model.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    boolean existsByEmail(String email);
}
