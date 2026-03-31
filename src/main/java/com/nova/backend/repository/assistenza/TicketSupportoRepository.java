package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.TicketSupporto;
import com.nova.backend.model.assistenza.StatoTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione dei ticket di supporto clienti.
 */
@Repository
public interface TicketSupportoRepository extends JpaRepository<TicketSupporto, Long> {

    /** Restituisce tutti i ticket aperti da un utente. */
    List<TicketSupporto> findByUtenteIdOrderByDataCreazioneDesc(Long utenteId);

    /** Filtra i ticket per stato. */
    List<TicketSupporto> findByStato(StatoTicket stato);
}
