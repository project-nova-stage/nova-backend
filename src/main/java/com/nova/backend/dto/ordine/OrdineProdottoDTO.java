package com.nova.backend.dto.ordine;

import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.ordine.OrdineProdotto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO per la riga dell'ordine.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdineProdottoDTO {

    private Long id;
    private Long prodottoId;
    private String nomeProdotto;
    private Integer quantita;
    private BigDecimal prezzoAcquisto;

    public static OrdineProdottoDTO fromEntity(OrdineProdotto entity) {
        if (entity == null) {
            return null;
        }
        Prodotto prodotto = entity.getProdotto();
        return OrdineProdottoDTO.builder()
                .id(entity.getId())
                .prodottoId(prodotto != null ? prodotto.getId() : null)
                .nomeProdotto(prodotto != null ? prodotto.getNome() : null)
                .quantita(entity.getQuantita())
                .prezzoAcquisto(entity.getPrezzoAcquisto())
                .build();
    }

    public static OrdineProdotto toEntity(OrdineProdottoDTO dto) {
        if (dto == null) {
            return null;
        }

        OrdineProdotto entity = new OrdineProdotto();
        entity.setId(dto.getId());
        entity.setQuantita(dto.getQuantita());
        entity.setPrezzoAcquisto(dto.getPrezzoAcquisto());
        // Nota: il prodotto deve essere risolto da livello servizio prima di salvare.
        return entity;
    }
}
