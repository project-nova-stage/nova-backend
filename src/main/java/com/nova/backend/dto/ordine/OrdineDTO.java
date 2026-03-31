package com.nova.backend.dto.ordine;

import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.StatoOrdine;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrdineDTO {
    private Long id;
    private Long utenteId;
    private String numeroOrdine;
    private StatoOrdine stato;
    private BigDecimal importoTotale;
    private Instant dataOrdine;
    private List<OrdineProdottoDTO> prodotti;

    public static Ordine toEntity(OrdineDTO dto) {
        if (dto == null) return null;
        Ordine ordine = new Ordine();
        ordine.setNumeroOrdine(dto.getNumeroOrdine());
        ordine.setStato(dto.getStato());
        ordine.setImportoTotale(dto.getImportoTotale());
        ordine.setDataOrdine(dto.getDataOrdine());
        return ordine;
    }

    public static OrdineDTO fromEntity(Ordine ordine) {
        if (ordine == null) return null;
        OrdineDTO dto = new OrdineDTO();
        dto.setId(ordine.getId());
        dto.setUtenteId(ordine.getUtente() != null ? ordine.getUtente().getId() : null);
        dto.setNumeroOrdine(ordine.getNumeroOrdine());
        dto.setStato(ordine.getStato());
        dto.setImportoTotale(ordine.getImportoTotale());
        dto.setDataOrdine(ordine.getDataOrdine());
        if (ordine.getProdotti() != null) {
            dto.setProdotti(ordine.getProdotti().stream()
                    .map(OrdineProdottoDTO::fromEntity)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
