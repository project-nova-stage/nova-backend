package com.nova.backend.utente;

import com.nova.backend.dto.utente.request.LoginRequestDTO;
import com.nova.backend.dto.utente.request.RegistroUtenteDTO;
import com.nova.backend.dto.utente.response.UserResponseDTO;
import com.nova.backend.mapper.utente.UtenteMapper;
import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.Utente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;

/**
 * Test di validazione per i DTO e Mapper del modulo Utente (in Main, struttura Request/Response).
 */
class UtenteDTOTest {

    @Test
    void testRegistroUtenteDTO() {
        RegistroUtenteDTO dto = new RegistroUtenteDTO();
        dto.setEmail("test@nova.it");
        dto.setNome("Mario");
        assertEquals("test@nova.it", dto.getEmail());
    }

    @Test
    void testUtenteMapper() {
        Utente entity = new Utente();
        entity.setId(10L);
        entity.setEmail("mapper@nova.it");
        entity.setAttivo(true);
        entity.setRuolo(Ruolo.CLIENTE);

        UserResponseDTO response = UtenteMapper.toResponse(entity);
        assertEquals(entity.getEmail(), response.getEmail());
        assertEquals("CLIENTE", response.getRuolo());
    }
}
