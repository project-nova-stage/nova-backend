package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.richiesta.TicketSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.TicketSupportoResponse;
import com.nova.backend.model.assistenza.TicketSupporto;
import com.nova.backend.model.utente.Utente;
import org.springframework.stereotype.Component;

@Component
public class TicketSupportoMapper {

    // Da DTO a Entity (quando ricevi dati dal frontend)
    public TicketSupporto toEntity(TicketSupportoRequest request) {
        TicketSupporto ticket = new TicketSupporto();

        // Usiamo i setter generati da Lombok
        ticket.setOggetto(request.subject());
        ticket.setPriorita(request.priority());

        // Gestione della relazione con l'Utente
        // Creiamo un'istanza vuota di Utente solo con l'ID per permettere a JPA di salvare la relazione
        if (request.userId() != null) {
            Utente utente = new Utente();
            utente.setId(request.userId());
            ticket.setUtente(utente);
        }

        return ticket;
    }

    // Da Entity a DTO (quando invii dati al frontend)
    public TicketSupportoResponse toResponse(TicketSupporto ticket) {
        // Estraiamo l'ID dell'utente in modo sicuro, evitando NullPointerException
        Long userId = (ticket.getUtente() != null) ? ticket.getUtente().getId() : null;

        return new TicketSupportoResponse(
                ticket.getId(),
                userId,
                ticket.getOggetto(),
                ticket.getPriorita(),
                ticket.getStato()
        );
    }
}