package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.richiesta.NotificaRequest;
import com.nova.backend.dto.assistenza.risposta.NotificaResponse;
import com.nova.backend.mapper.assistenza.NotificaMapper;
import com.nova.backend.model.assistenza.Notifica;
import com.nova.backend.repository.assistenza.NotificaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificaService {

    private final NotificaRepository repository;
    private final NotificaMapper mapper;

    public NotificaService(NotificaRepository repository, NotificaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public NotificaResponse inviaNotifica(NotificaRequest request) {
        Notifica notifica = mapper.toEntity(request);
        return mapper.toResponse(repository.save(notifica));
    }

    public List<NotificaResponse> ottieniPerUtente(Long utenteId) {
        return repository.findByUtenteId(utenteId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public List<NotificaResponse> ottieniNonLette(Long utenteId) {
        return repository.findByUtenteIdAndLettaFalse(utenteId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public void segnaComeLetta(Long id) {
        repository.findById(id).ifPresent(notifica -> {
            notifica.setLetta(true);
            repository.save(notifica);
        });
    }
}