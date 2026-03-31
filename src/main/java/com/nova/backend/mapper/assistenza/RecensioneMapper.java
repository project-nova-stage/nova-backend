package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.richiesta.RecensioneRequest;
import com.nova.backend.dto.assistenza.risposta.RecensioneResponse;
import com.nova.backend.model.assistenza.Recensione;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.utente.Utente;
import org.springframework.stereotype.Component;

@Component
public class RecensioneMapper {

    public Recensione toEntity(RecensioneRequest request) {
        Recensione recensione = new Recensione();
        recensione.setValutazione(request.rating());
        recensione.setCommento(request.comment());

        if (request.userId() != null) {
            Utente utente = new Utente();
            utente.setId(request.userId());
            recensione.setUtente(utente);
        }

        if (request.productId() != null) {
            Prodotto prodotto = new Prodotto();
            prodotto.setId(request.productId());
            recensione.setProdotto(prodotto);
        }

        return recensione;
    }

    public RecensioneResponse toResponse(Recensione recensione) {
        Long userId = (recensione.getUtente() != null) ? recensione.getUtente().getId() : null;
        Long productId = (recensione.getProdotto() != null) ? recensione.getProdotto().getId() : null;

        return new RecensioneResponse(
                recensione.getId(),
                userId,
                productId,
                recensione.getValutazione(),
                recensione.getCommento(),
                recensione.getDataCreazione()
        );
    }
}