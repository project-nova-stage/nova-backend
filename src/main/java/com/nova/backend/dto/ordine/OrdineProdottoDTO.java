package com.nova.backend.dto.ordine;

import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.ordine.OrdineProdotto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrdineProdottoDTO {
    private Long id;
    private Long prodottoId;
    private Integer quantita;
    private BigDecimal prezzoAcquisto;

    public static OrdineProdotto toEntity(OrdineProdottoDTO dto) {
        if (dto == null) return null;
        Prodotto prodotto = new Prodotto();
        prodotto.setId(dto.getProdottoId());
        OrdineProdotto op = new OrdineProdotto();
        op.setProdotto(prodotto);
        op.setQuantita(dto.getQuantita());
        op.setPrezzoAcquisto(dto.getPrezzoAcquisto());
        return op;
    }

    public static OrdineProdottoDTO fromEntity(OrdineProdotto op) {
        if (op == null) return null;
        OrdineProdottoDTO dto = new OrdineProdottoDTO();
        dto.setId(op.getId());
        dto.setProdottoId(op.getProdotto() != null ? op.getProdotto().getId() : null);
        dto.setQuantita(op.getQuantita());
        dto.setPrezzoAcquisto(op.getPrezzoAcquisto());
        return dto;
    }
}
