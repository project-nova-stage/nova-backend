package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.richiesta.MessaggioSupportoRequest;
import com.nova.backend.dto.assistenza.richiesta.TicketSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.MessaggioSupportoResponse;
import com.nova.backend.dto.assistenza.risposta.TicketSupportoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportoService {

    private final TicketSupportoService ticketService;
    private final MessaggioSupportoService messaggioService;

    public SupportoService(TicketSupportoService ticketService, MessaggioSupportoService messaggioService) {
        this.ticketService = ticketService;
        this.messaggioService = messaggioService;
    }

    // Ticket
    public TicketSupportoResponse creaTicket(TicketSupportoRequest request) {
        return ticketService.creaTicket(request);
    }

    public List<TicketSupportoResponse> ottieniTuttiITicket() {
        return ticketService.ottieniTutti();
    }

    public Optional<TicketSupportoResponse> ottieniTicketPerId(Long id) {
        return ticketService.ottieniPerId(id);
    }

    public TicketSupportoResponse aggiornaTicket(Long id, TicketSupportoRequest request) {
        return ticketService.aggiornaTicket(id, request);
    }

    public void eliminaTicket(Long id) {
        ticketService.eliminaTicket(id);
    }

    // Messaggi
    public MessaggioSupportoResponse aggiungiMessaggio(MessaggioSupportoRequest request) {
        return messaggioService.aggiungiMessaggio(request);
    }

    public List<MessaggioSupportoResponse> ottieniMessaggiPerTicket(Long ticketId) {
        return messaggioService.ottieniMessaggiPerTicket(ticketId);
    }

}
