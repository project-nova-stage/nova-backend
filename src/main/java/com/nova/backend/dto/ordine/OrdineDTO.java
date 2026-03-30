package com.nova.backend.dto.ordine;

import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.OrdineProdotto;
import com.nova.backend.model.ordine.StatoOrdine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO di trasporto dell'ordine.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdineDTO {

    private Long id;
    private Long utenteId;
    private String numeroOrdine;
    private StatoOrdine stato;
    private BigDecimal importoTotale;
    private Instant dataOrdine;
    private List<OrdineProdottoDTO> prodotti;

    public static OrdineDTO fromEntity(Ordine entity) {
        if (entity == null) {
            return null;
        }

        return OrdineDTO.builder()
                .id(entity.getId())
                .utenteId(entity.getUtente() != null ? entity.getUtente().getId() : null)
                .numeroOrdine(entity.getNumeroOrdine())
                .stato(entity.getStato())
                .importoTotale(entity.getImportoTotale())
                .dataOrdine(entity.getDataOrdine())
                .prodotti(entity.getProdotti() == null ? Collections.emptyList() :
                        entity.getProdotti().stream()
                                .map(OrdineProdottoDTO::fromEntity)
                                .collect(Collectors.toList()))
                .build();
    }

    public static Ordine toEntity(OrdineDTO dto) {
        if (dto == null) {
            return null;
        }

        Ordine entity = new Ordine();
        entity.setId(dto.getId());
        entity.setNumeroOrdine(dto.getNumeroOrdine());
        entity.setStato(dto.getStato());
        entity.setImportoTotale(dto.getImportoTotale());
        entity.setDataOrdine(dto.getDataOrdine());
        if (dto.getProdotti() != null) {
            List<OrdineProdotto> prodotti = dto.getProdotti().stream()
                    .map(OrdineProdottoDTO::toEntity)
                    .collect(Collectors.toList());
            prodotti.forEach(p -> p.setOrdine(entity));
            entity.setProdotti(prodotti);
        }
        return entity;
    }
}
