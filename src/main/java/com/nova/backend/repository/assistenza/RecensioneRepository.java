package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione delle recensioni sui prodotti.
 */
@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Long> {

    /** Recupera tutte le recensioni di un prodotto. */
    List<Recensione> findByProdottoId(Long prodottoId);

    /** Per la dashboard dell'utente (le sue recensioni). */
    List<Recensione> findByUtenteId(Long utenteId);

    /** Verifica se un utente ha già recensito un dato prodotto (una sola recensione per utente/prodotto). */
    boolean existsByUtenteIdAndProdottoId(Long utenteId, Long prodottoId);
}
