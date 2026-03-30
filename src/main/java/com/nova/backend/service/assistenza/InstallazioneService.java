package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.InstallazioneRequest;
import com.nova.backend.dto.assistenza.InstallazioneResponse;
import com.nova.backend.mapper.assistenza.InstallazioneMapper;
import com.nova.backend.model.assistenza.Installazione;
import com.nova.backend.model.assistenza.StatoInstallazione;
import com.nova.backend.repository.assistenza.InstallazioneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstallazioneService {

    private final InstallazioneRepository repository;
    private final InstallazioneMapper mapper;

    public InstallazioneService(InstallazioneRepository repository, InstallazioneMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InstallazioneResponse pianificaInstallazione(InstallazioneRequest request) {
        Installazione installazione = mapper.toEntity(request);
        installazione.setStato(StatoInstallazione.PROGRAMMATA); // Stato di default
        Installazione salvata = repository.save(installazione);
        return mapper.toResponse(salvata);
    }

    public List<InstallazioneResponse> ottieniTutte() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public List<InstallazioneResponse> ottieniPerTecnico(Long tecnicoId) {
        return repository.findByTecnicoId(tecnicoId).stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    public InstallazioneResponse aggiornaStato(Long id, StatoInstallazione nuovoStato) {
        return repository.findById(id).map(installazione -> {
            installazione.setStato(nuovoStato);
            return mapper.toResponse(repository.save(installazione));
        }).orElseThrow(() -> new RuntimeException("Installazione non trovata"));
    }
}