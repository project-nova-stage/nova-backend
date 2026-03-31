package com.nova.backend.mapper.assistenza;

import com.nova.backend.dto.assistenza.risposta.MessaggioSupportoDTO;
import com.nova.backend.dto.assistenza.risposta.RichiestaServizioDTO;
import com.nova.backend.dto.assistenza.risposta.TicketSupportoDTO;
import com.nova.backend.model.assistenza.MessaggioSupporto;
import com.nova.backend.model.assistenza.RichiestaServizio;
import com.nova.backend.model.assistenza.TicketSupporto;

/**
 * Mapper ufficiale del modulo Assistenza & Supporto.
 */
public class AssistenzaMapper {

    public static TicketSupportoDTO toTicketDTO(TicketSupporto ticket) {
        if (ticket == null) return null;

        TicketSupportoDTO dto = new TicketSupportoDTO();
        dto.setId(ticket.getId());
        dto.setSubject(ticket.getOggetto());
        dto.setPriority(ticket.getPriorita() != null ? ticket.getPriorita().name() : null);
        dto.setStatus(ticket.getStato() != null ? ticket.getStato().name() : null);
        dto.setCreatedAt(ticket.getDataCreazione());
        return dto;
    }

    public static MessaggioSupportoDTO toMessaggioDTO(MessaggioSupporto msg) {
        if (msg == null) return null;

        MessaggioSupportoDTO dto = new MessaggioSupportoDTO();
        dto.setId(msg.getId());
        if (msg.getMittente() != null) {
            dto.setSenderName(msg.getMittente().getNome() + " " + msg.getMittente().getCognome());
        }
        dto.setContent(msg.getContenuto());
        dto.setCreatedAt(msg.getDataCreazione());
        return dto;
    }

    public static RichiestaServizioDTO toRichiestaDTO(RichiestaServizio richiesta) {
        if (richiesta == null) return null;

        RichiestaServizioDTO dto = new RichiestaServizioDTO();
        dto.setId(richiesta.getId());
        dto.setProjectType(richiesta.getTipoProgetto() != null ? richiesta.getTipoProgetto().name() : null);
        dto.setStatus(richiesta.getStato() != null ? richiesta.getStato().name() : null);
        dto.setCreatedAt(richiesta.getDataCreazione());
        return dto;
    }
}
