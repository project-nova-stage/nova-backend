package com.nova.backend.assistenza;

import com.nova.backend.dto.assistenza.response.MessaggioSupportoDTO;
import com.nova.backend.dto.assistenza.response.TicketSupportoDTO;
import com.nova.backend.mapper.assistenza.AssistenzaMapper;
import com.nova.backend.model.assistenza.PrioritaTicket;
import com.nova.backend.model.assistenza.StatoTicket;
import com.nova.backend.model.assistenza.TicketSupporto;
import com.nova.backend.model.assistenza.MessaggioSupporto;
import com.nova.backend.model.utente.Utente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

/**
 * Test di validazione per i DTO e Mapper del modulo Assistenza (in Main).
 */
class AssistenzaDTOTest {

    @Test
    void testTicketMapping() {
        TicketSupporto t = new TicketSupporto();
        t.setId(1L);
        t.setOggetto("Problemi con l'inverter");
        t.setPriorita(PrioritaTicket.ALTA);
        t.setStato(StatoTicket.APERTO);
        t.setDataCreazione(Instant.now());

        TicketSupportoDTO dto = AssistenzaMapper.toTicketDTO(t);

        assertEquals("Problemi con l'inverter", dto.getSubject());
        assertEquals("ALTA", dto.getPriority());
    }

    @Test
    void testMessaggioMapping() {
        Utente u = new Utente();
        u.setNome("Mario");
        u.setCognome("Rossi");

        MessaggioSupporto msg = new MessaggioSupporto();
        msg.setId(10L);
        msg.setMittente(u);
        msg.setContenuto("Ciao!");
        msg.setDataCreazione(Instant.now());

        MessaggioSupportoDTO dto = AssistenzaMapper.toMessaggioDTO(msg);
        assertEquals("Mario Rossi", dto.getSenderName());
    }
}
