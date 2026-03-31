package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.TicketSupportoRequest;
import com.nova.backend.dto.assistenza.TicketSupportoResponse;
import com.nova.backend.mapper.assistenza.TicketSupportoMapper;
import com.nova.backend.model.assistenza.StatoTicket;
import com.nova.backend.model.assistenza.TicketSupporto;
import com.nova.backend.repository.assistenza.TicketSupportoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketSupportoService {

    private final TicketSupportoRepository repository;
    private final TicketSupportoMapper mapper;

    public TicketSupportoService(TicketSupportoRepository repository, TicketSupportoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    public TicketSupportoResponse creaTicket(TicketSupportoRequest request) {
        TicketSupporto ticket = mapper.toEntity(request);
        ticket.setStato(StatoTicket.APERTO); // Modificato in setStato()
        TicketSupporto salvato = repository.save(ticket);
        return mapper.toResponse(salvato);
    }

    // READ (Tutti)
    public List<TicketSupportoResponse> ottieniTutti() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ (Singolo)
    public Optional<TicketSupportoResponse> ottieniPerId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse);
    }

    // UPDATE
    public TicketSupportoResponse aggiornaTicket(Long id, TicketSupportoRequest request) {
        return repository.findById(id).map(ticketEsistente -> {
            ticketEsistente.setOggetto(request.subject()); // Modificato in setOggetto()
            ticketEsistente.setPriorita(request.priority()); // Modificato in setPriorita()
            TicketSupporto aggiornato = repository.save(ticketEsistente);
            return mapper.toResponse(aggiornato);
        }).orElseThrow(() -> new RuntimeException("Ticket non trovato"));
    }

    // DELETE
    public void eliminaTicket(Long id) {
        repository.deleteById(id);
    }
}