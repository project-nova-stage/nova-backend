package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.richiesta.RichiestaServizioRequest;
import com.nova.backend.dto.assistenza.risposta.RichiestaServizioResponse;
import com.nova.backend.mapper.assistenza.RichiestaServizioMapper;
import com.nova.backend.model.assistenza.RichiestaServizio;
import com.nova.backend.model.assistenza.StatoServizio;
import com.nova.backend.repository.assistenza.RichiestaServizioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RichiestaServizioService {

    private final RichiestaServizioRepository repository;
    private final RichiestaServizioMapper mapper;

    public RichiestaServizioService(RichiestaServizioRepository repository, RichiestaServizioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RichiestaServizioResponse creaRichiesta(RichiestaServizioRequest request) {
        RichiestaServizio richiesta = mapper.toEntity(request);
        // Impostiamo lo stato iniziale (assicurati che "NUOVA" sia nel tuo enum StatoServizio)
        richiesta.setStato(StatoServizio.NUOVA);
        RichiestaServizio salvata = repository.save(richiesta);
        return mapper.toResponse(salvata);
    }

    public List<RichiestaServizioResponse> ottieniTutte() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<RichiestaServizioResponse> ottieniPerId(Long id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public List<RichiestaServizioResponse> ottieniPerUtente(Long utenteId) {
        return repository.findByUtenteId(utenteId).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public RichiestaServizioResponse aggiornaStato(Long id, StatoServizio nuovoStato) {
        return repository.findById(id).map(richiesta -> {
            richiesta.setStato(nuovoStato);
            return mapper.toResponse(repository.save(richiesta));
        }).orElseThrow(() -> new RuntimeException("Richiesta di servizio non trovata"));
    }
}