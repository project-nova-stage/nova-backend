package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.InstallazioneRequest;
import com.nova.backend.dto.assistenza.InstallazioneResponse;
import com.nova.backend.model.assistenza.Installazione;
import com.nova.backend.model.assistenza.RichiestaServizio;
import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.utente.Utente;
import org.springframework.stereotype.Component;

@Component
public class InstallazioneMapper {

    public Installazione toEntity(InstallazioneRequest request) {
        Installazione installazione = new Installazione();
        installazione.setDataPianificata(request.scheduledDate());

        if (request.serviceRequestId() != null) {
            RichiestaServizio rs = new RichiestaServizio();
            rs.setId(request.serviceRequestId());
            installazione.setRichiestaServizio(rs);
        }

        if (request.orderId() != null) {
            Ordine ordine = new Ordine();
            ordine.setId(request.orderId());
            installazione.setOrdine(ordine);
        }

        if (request.technicianId() != null) {
            Utente tecnico = new Utente();
            tecnico.setId(request.technicianId());
            installazione.setTecnico(tecnico);
        }

        return installazione;
    }

    public InstallazioneResponse toResponse(Installazione installazione) {
        Long serviceRequestId = (installazione.getRichiestaServizio() != null) ? installazione.getRichiestaServizio().getId() : null;
        Long orderId = (installazione.getOrdine() != null) ? installazione.getOrdine().getId() : null;
        Long technicianId = (installazione.getTecnico() != null) ? installazione.getTecnico().getId() : null;

        return new InstallazioneResponse(
                installazione.getId(),
                serviceRequestId,
                orderId,
                technicianId,
                installazione.getDataPianificata(),
                installazione.getStato()
        );
    }
}