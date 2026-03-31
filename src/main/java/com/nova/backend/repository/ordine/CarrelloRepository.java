package com.nova.backend.repository.ordine;

import com.nova.backend.model.ordine.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository per la gestione dei carrelli degli utenti.
 */
@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

    /** Recupera il carrello attivo di un utente. Ogni utente ha un solo carrello persistente. */
    Optional<Carrello> findByUtenteId(Long utenteId);
}
