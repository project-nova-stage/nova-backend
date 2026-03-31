package com.nova.backend.mapper.utente;

import com.nova.backend.dto.utente.request.RegistroUtenteDTO;
import com.nova.backend.dto.utente.response.UserResponseDTO;
import com.nova.backend.model.utente.Utente;

/**
 * Mapper per le conversioni tra l'entità Utente e i relativi DTO (Request/Response).
 */
public class UtenteMapper {

    public static UserResponseDTO toResponse(Utente utente) {
        if (utente == null) return null;
        
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(utente.getId());
        dto.setEmail(utente.getEmail());
        dto.setNome(utente.getNome());
        dto.setCognome(utente.getCognome());
        dto.setRuolo(utente.getRuolo() != null ? utente.getRuolo().name() : null);
        dto.setCreatedAt(utente.getCreatedAt());
        dto.setAttivo(utente.isAttivo());
        
        return dto;
    }

    public static Utente toEntity(RegistroUtenteDTO dto) {
        if (dto == null) return null;

        Utente utente = new Utente();
        utente.setEmail(dto.getEmail());
        utente.setPassword(dto.getPassword());
        utente.setNome(dto.getNome());
        utente.setCognome(dto.getCognome());
        
        return utente;
    }
}
