package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.richiesta.MessaggioSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.MessaggioSupportoResponse;
import com.nova.backend.model.assistenza.MessaggioSupporto;
import com.nova.backend.model.assistenza.TicketSupporto;
import com.nova.backend.model.utente.Utente;
import org.springframework.stereotype.Component;

@Component
public class MessaggioSupportoMapper {

    public MessaggioSupporto toEntity(MessaggioSupportoRequest request) {
        MessaggioSupporto messaggio = new MessaggioSupporto();
        messaggio.setContenuto(request.content());

        if (request.ticketId() != null) {
            TicketSupporto ticket = new TicketSupporto();
            ticket.setId(request.ticketId());
            messaggio.setTicket(ticket);
        }

        if (request.senderId() != null) {
            Utente mittente = new Utente();
            mittente.setId(request.senderId());
            messaggio.setMittente(mittente);
        }

        return messaggio;
    }

    public MessaggioSupportoResponse toResponse(MessaggioSupporto messaggio) {
        Long ticketId = (messaggio.getTicket() != null) ? messaggio.getTicket().getId() : null;
        Long senderId = (messaggio.getMittente() != null) ? messaggio.getMittente().getId() : null;

        return new MessaggioSupportoResponse(
                messaggio.getId(),
                ticketId,
                senderId,
                messaggio.getContenuto(),
                messaggio.getDataCreazione()
        );
    }
}