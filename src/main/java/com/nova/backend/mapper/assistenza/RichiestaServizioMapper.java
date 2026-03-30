package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.RichiestaServizioRequest;
import com.nova.backend.dto.assistenza.RichiestaServizioResponse;
import com.nova.backend.model.assistenza.RichiestaServizio;
import com.nova.backend.model.utente.Utente;
import org.springframework.stereotype.Component;

@Component
public class RichiestaServizioMapper {

    public RichiestaServizio toEntity(RichiestaServizioRequest request) {
        RichiestaServizio richiesta = new RichiestaServizio();
        richiesta.setTipoProgetto(request.projectType());
        richiesta.setDescrizione(request.description());

        if (request.userId() != null) {
            Utente utente = new Utente();
            utente.setId(request.userId());
            richiesta.setUtente(utente);
        }

        return richiesta;
    }

    public RichiestaServizioResponse toResponse(RichiestaServizio richiesta) {
        Long userId = (richiesta.getUtente() != null) ? richiesta.getUtente().getId() : null;

        return new RichiestaServizioResponse(
                richiesta.getId(),
                userId,
                richiesta.getTipoProgetto(),
                richiesta.getDescrizione(),
                richiesta.getStato(),
                richiesta.getDataCreazione()
        );
    }
}