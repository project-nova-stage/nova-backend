package com.nova.backend.repository.ordine;

import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.StatoOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository per la gestione degli ordini.
 */
@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    /** Restituisce tutti gli ordini di un utente, ordinati per data decrescente. */
    List<Ordine> findByUtenteIdOrderByDataOrdineDesc(Long utenteId);

    /** Recupera un ordine tramite il numero d'ordine univoco (es. ORD-2026-001). */
    Optional<Ordine> findByNumeroOrdine(String numeroOrdine);

    /** Filtra gli ordini per stato (es. tutti quelli in lavorazione). */
    List<Ordine> findByStato(StatoOrdine stato);

    /**
 * Retrieve all orders belonging to the specified user without applying a default sort.
 *
 * @param utenteId the identifier of the user whose orders are requested
 * @return a list of orders for the specified user; an empty list if the user has no orders
 */
    List<Ordine> findByUtenteId(Long utenteId);


}
