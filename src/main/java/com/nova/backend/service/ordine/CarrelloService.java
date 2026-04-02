package com.nova.backend.service.ordine;

import com.nova.backend.dto.ordine.richiesta.CartItemRequestDTO;
import com.nova.backend.dto.ordine.risposta.CartResponseDTO;
import com.nova.backend.exception.EccezioneApplicativa;
import com.nova.backend.mapper.ordine.OrdineMapper;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.ordine.Carrello;
import com.nova.backend.model.ordine.CarrelloProdotto;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.catalogo.ProdottoRepository;
import com.nova.backend.repository.ordine.CarrelloRepository;
import com.nova.backend.repository.utente.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Servizio per la gestione del carrello utente.
 */
@Service
@Transactional
public class CarrelloService {

    private final CarrelloRepository carrelloRepository;
    private final UtenteRepository utenteRepository;
    private final ProdottoRepository prodottoRepository;

    public CarrelloService(CarrelloRepository carrelloRepository,
                           UtenteRepository utenteRepository,
                           ProdottoRepository prodottoRepository) {
        this.carrelloRepository = carrelloRepository;
        this.utenteRepository = utenteRepository;
        this.prodottoRepository = prodottoRepository;
    }

    public CartResponseDTO ottieniCarrello(Long utenteId) {
        return OrdineMapper.toCartResponse(ottieniOCreaCarrello(utenteId));
    }

    public CartResponseDTO aggiungiProdotto(Long utenteId, CartItemRequestDTO request) {
        Carrello carrello = ottieniOCreaCarrello(utenteId);
        Prodotto prodotto = prodottoRepository.findById(request.getProdottoId())
                .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Prodotto non trovato con ID: " + request.getProdottoId(), org.springframework.http.HttpStatus.NOT_FOUND));

        if (Boolean.FALSE.equals(prodotto.getAttivo())) {
            throw new com.nova.backend.exception.EccezioneApplicativa("Prodotto non attivo", org.springframework.http.HttpStatus.NOT_FOUND);
        }

        if (carrello.getProdotti() == null) {
            carrello.setProdotti(new ArrayList<>());
        }

        CarrelloProdotto riga = carrello.getProdotti().stream()
                .filter(cp -> cp.getProdotto().getId().equals(prodotto.getId()))
                .findFirst()
                .orElse(null);

        if (riga == null) {
            riga = new CarrelloProdotto(carrello, prodotto, request.getQuantita());
            carrello.getProdotti().add(riga);
        } else {
            riga.setQuantita(riga.getQuantita() + request.getQuantita());
        }

        Carrello salvato = carrelloRepository.save(carrello);
        return OrdineMapper.toCartResponse(salvato);
    }

    public CartResponseDTO aggiornaQuantita(Long utenteId, Long prodottoId, Integer quantita) {
        Carrello carrello = ottieniOCreaCarrello(utenteId);
        if (carrello.getProdotti() == null) {
            carrello.setProdotti(new ArrayList<>());
        }

        CarrelloProdotto riga = carrello.getProdotti().stream()
                .filter(cp -> cp.getProdotto().getId().equals(prodottoId))
                .findFirst()
                .orElseThrow(() -> new EccezioneApplicativa("Prodotto non presente nel carrello", org.springframework.http.HttpStatus.NOT_FOUND));

        if (quantita <= 0) {
            carrello.getProdotti().remove(riga);
        } else {
            riga.setQuantita(quantita);
        }

        Carrello salvato = carrelloRepository.save(carrello);
        return OrdineMapper.toCartResponse(salvato);
    }

    public CartResponseDTO rimuoviProdotto(Long utenteId, Long prodottoId) {
        Carrello carrello = ottieniOCreaCarrello(utenteId);
        if (carrello.getProdotti() == null) {
            carrello.setProdotti(new ArrayList<>());
        }

        carrello.getProdotti().removeIf(cp -> cp.getProdotto().getId().equals(prodottoId));
        Carrello salvato = carrelloRepository.save(carrello);
        return OrdineMapper.toCartResponse(salvato);
    }

    public void svuotaCarrello(Long utenteId) {
        Carrello carrello = ottieniOCreaCarrello(utenteId);
        if (carrello.getProdotti() != null) {
            carrello.getProdotti().clear();
        }
        carrelloRepository.save(carrello);
    }

    private Carrello ottieniOCreaCarrello(Long utenteId) {
        return carrelloRepository.findByUtenteId(utenteId)
                .orElseGet(() -> {
                    Utente utente = utenteRepository.findById(utenteId)
                            .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Utente non trovato con ID: " + utenteId, org.springframework.http.HttpStatus.NOT_FOUND));
                    Carrello nuovo = new Carrello(utente);
                    nuovo.setProdotti(new ArrayList<>());
                    return carrelloRepository.save(nuovo);
                });
    }
}

