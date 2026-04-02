package com.nova.backend.service.ordine;

import com.nova.backend.dto.ordine.OrdineDTO;
import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.StatoOrdine;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.ordine.OrdineRepository;
import com.nova.backend.repository.utente.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdineServiceImpl implements OrdineService {

    private final OrdineRepository ordineRepository;
    private final UtenteRepository utenteRepository;

    public OrdineServiceImpl(OrdineRepository ordineRepository, UtenteRepository utenteRepository) {
        this.ordineRepository = ordineRepository;
        this.utenteRepository = utenteRepository;
    }

    @Override
    public OrdineDTO creaOrdine(OrdineDTO ordineDTO) {
        if (ordineDTO == null) {
            throw new com.nova.backend.exception.EccezioneApplicativa("OrdineDTO non puÃƒÂ² essere null", org.springframework.http.HttpStatus.BAD_REQUEST);
        }

        Ordine ordine = OrdineDTO.toEntity(ordineDTO);

        if (ordineDTO.getUtenteId() != null) {
            Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                    .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Utente non trovato con id " + ordineDTO.getUtenteId(), org.springframework.http.HttpStatus.BAD_REQUEST));
            ordine.setUtente(utente);
        }

        Ordine salvato = ordineRepository.save(ordine);
        return OrdineDTO.fromEntity(salvato);
    }

    @Override
    public OrdineDTO getOrdineById(Long id) {
        return ordineRepository.findById(id)
                .map(OrdineDTO::fromEntity)
                .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Ordine non trovato con id " + id, org.springframework.http.HttpStatus.BAD_REQUEST));
    }

    @Override
    public List<OrdineDTO> getOrdiniByUtenteId(Long utenteId) {
        return ordineRepository.findByUtenteId(utenteId).stream()
                .map(OrdineDTO::fromEntity)
                .toList();
    }

    @Override
    public List<OrdineDTO> getOrdiniByStato(StatoOrdine stato) {
        return ordineRepository.findByStato(stato).stream()
                .map(OrdineDTO::fromEntity)
                .toList();
    }

    @Override
    public List<OrdineDTO> getAllOrdini() {
        return ordineRepository.findAll().stream()
                .map(OrdineDTO::fromEntity)
                .toList();
    }

    @Override
    public OrdineDTO aggiornaOrdine(Long id, OrdineDTO ordineDTO) {
        Ordine esistente = ordineRepository.findById(id)
                .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Ordine non trovato con id " + id, org.springframework.http.HttpStatus.BAD_REQUEST));

        if (ordineDTO.getUtenteId() != null) {
            Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                    .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Utente non trovato con id " + ordineDTO.getUtenteId(), org.springframework.http.HttpStatus.BAD_REQUEST));
            esistente.setUtente(utente);
        }

        esistente.setNumeroOrdine(ordineDTO.getNumeroOrdine());
        esistente.setStato(ordineDTO.getStato());
        esistente.setImportoTotale(ordineDTO.getImportoTotale());
        esistente.setDataOrdine(ordineDTO.getDataOrdine());

        if (ordineDTO.getProdotti() != null) {
            esistente.getProdotti().clear();
            esistente.getProdotti().addAll(ordineDTO.getProdotti().stream().map(o -> {
                com.nova.backend.model.ordine.OrdineProdotto op = com.nova.backend.dto.ordine.OrdineProdottoDTO.toEntity(o);
                op.setOrdine(esistente);
                return op;
            }).toList());
        }

        Ordine aggiornato = ordineRepository.save(esistente);
        return OrdineDTO.fromEntity(aggiornato);
    }

    @Override
    public void eliminaOrdine(Long id) {
        if (!ordineRepository.existsById(id)) {
            throw new com.nova.backend.exception.EccezioneApplicativa("Ordine non trovato con id " + id, org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        ordineRepository.deleteById(id);
    }
}

