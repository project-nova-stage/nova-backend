package com.nova.backend.service.ordine;

import com.nova.backend.dto.ordine.OrdineDTO;
import com.nova.backend.model.ordine.StatoOrdine;

import java.util.List;

public interface OrdineService {

    OrdineDTO creaOrdine(OrdineDTO ordineDTO);

    OrdineDTO getOrdineById(Long id);

    List<OrdineDTO> getOrdiniByUtenteId(Long utenteId);

    List<OrdineDTO> getOrdiniByStato(StatoOrdine stato);

    List<OrdineDTO> getAllOrdini();

    OrdineDTO aggiornaOrdine(Long id, OrdineDTO ordineDTO);

    void eliminaOrdine(Long id);
}
