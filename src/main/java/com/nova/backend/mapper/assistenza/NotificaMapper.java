package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.NotificaRequest;
import com.nova.backend.dto.assistenza.NotificaResponse;
import com.nova.backend.model.assistenza.Notifica;
import com.nova.backend.model.utente.Utente;
import org.springframework.stereotype.Component;

@Component
public class NotificaMapper {

    public Notifica toEntity(NotificaRequest request) {
        Notifica notifica = new Notifica();
        notifica.setTitolo(request.title());
        notifica.setMessaggio(request.message());
        // Il flag 'letta' è a false di default come da te impostato nell'Entity

        if (request.userId() != null) {
            Utente utente = new Utente();
            utente.setId(request.userId());
            notifica.setUtente(utente);
        }

        return notifica;
    }

    public NotificaResponse toResponse(Notifica notifica) {
        Long userId = (notifica.getUtente() != null) ? notifica.getUtente().getId() : null;

        return new NotificaResponse(
                notifica.getId(),
                userId,
                notifica.getTitolo(),
                notifica.getMessaggio(),
                notifica.getLetta(),
                notifica.getDataCreazione()
        );
    }
}