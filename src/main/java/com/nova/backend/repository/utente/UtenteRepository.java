package com.nova.backend.repository.utente;

import com.nova.backend.model.utente.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository per la gestione degli utenti nel database.
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    /** Recupera un utente tramite indirizzo email. Usato principalmente in fase di login. */
    Optional<Utente> findByEmail(String email);

    /** Verifica l'esistenza di un utente con una data email. Usato per prevenire duplicati in fase di registrazione. */
    boolean existsByEmail(String email);
}
