package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.richiesta.RecensioneRequest;
import com.nova.backend.dto.assistenza.risposta.RecensioneResponse;
import com.nova.backend.mapper.assistenza.RecensioneMapper;
import com.nova.backend.model.assistenza.Recensione;
import com.nova.backend.repository.assistenza.RecensioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecensioneService {

    private final RecensioneRepository repository;
    private final RecensioneMapper mapper;

    public RecensioneService(RecensioneRepository repository, RecensioneMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RecensioneResponse creaRecensione(RecensioneRequest request) {
        // Se il DB lancia errore per il vincolo Unique, Spring restituirà un DataIntegrityViolationException
        Recensione recensione = mapper.toEntity(request);
        return mapper.toResponse(repository.save(recensione));
    }

    public List<RecensioneResponse> ottieniPerProdotto(Long prodottoId) {
        return repository.findByProdottoId(prodottoId).stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public void eliminaRecensione(Long id) {
        repository.deleteById(id);
    }
}