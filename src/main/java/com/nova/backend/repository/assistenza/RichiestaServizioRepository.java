package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.RichiestaServizio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione delle richieste di sopralluogo e preventivo.
 */
@Repository
public interface RichiestaServizioRepository extends JpaRepository<RichiestaServizio, Long> {

    /** Recupera tutte le richieste inviate da un utente. */
    List<RichiestaServizio> findByUtenteIdOrderByDataCreazioneDesc(Long utenteId);

    /** Trova tutte le richieste fatte da un utente specifico. */
    List<RichiestaServizio> findByUtenteId(Long utenteId);
}
