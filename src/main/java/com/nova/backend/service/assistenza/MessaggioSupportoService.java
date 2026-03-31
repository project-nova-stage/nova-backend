package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.richiesta.MessaggioSupportoRequest;
import com.nova.backend.dto.assistenza.risposta.MessaggioSupportoResponse;
import com.nova.backend.mapper.assistenza.MessaggioSupportoMapper;
import com.nova.backend.model.assistenza.MessaggioSupporto;
import com.nova.backend.repository.assistenza.MessaggioSupportoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessaggioSupportoService {

    private final MessaggioSupportoRepository repository;
    private final MessaggioSupportoMapper mapper;

    public MessaggioSupportoService(MessaggioSupportoRepository repository, MessaggioSupportoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MessaggioSupportoResponse aggiungiMessaggio(MessaggioSupportoRequest request) {
        MessaggioSupporto messaggio = mapper.toEntity(request);
        MessaggioSupporto salvato = repository.save(messaggio);
        return mapper.toResponse(salvato);
    }

    public List<MessaggioSupportoResponse> ottieniMessaggiPerTicket(Long ticketId) {
        return repository.findByTicketId(ticketId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}