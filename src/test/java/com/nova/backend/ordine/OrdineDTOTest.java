package com.nova.backend.ordine;

import com.nova.backend.dto.ordine.response.CartResponseDTO;
import com.nova.backend.dto.ordine.response.OrderResponseDTO;
import com.nova.backend.mapper.ordine.OrdineMapper;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.ordine.Carrello;
import com.nova.backend.model.ordine.CarrelloProdotto;
import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.OrdineProdotto;
import com.nova.backend.model.ordine.StatoOrdine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * Test di validazione per i DTO e Mapper del modulo Ordine (in Main).
 */
class OrdineDTOTest {

    @Test
    void testCartMappingAndTotals() {
        Prodotto p1 = new Prodotto();
        p1.setNome("Prodotto A");
        p1.setPrezzo(new BigDecimal("10.00"));

        Carrello carrello = new Carrello();
        carrello.setId(1L);
        
        CarrelloProdotto cp = new CarrelloProdotto();
        cp.setProdotto(p1);
        cp.setQuantita(2);
        carrello.setProdotti(List.of(cp));

        CartResponseDTO response = OrdineMapper.toCartResponse(carrello);
        assertEquals(new BigDecimal("20.00"), response.getTotalAmount());
    }

    @Test
    void testOrderPriceSnapshotMapping() {
        Prodotto p = new Prodotto();
        p.setNome("Laptop");
        p.setPrezzo(new BigDecimal("1500.00"));

        Ordine ordine = new Ordine();
        ordine.setNumeroOrdine("ORD-123");
        ordine.setStato(StatoOrdine.PAGATO);
        ordine.setImportoTotale(new BigDecimal("1200.00"));
        ordine.setDataOrdine(Instant.now());

        OrdineProdotto op = new OrdineProdotto(ordine, p, 1, new BigDecimal("1200.00"));
        ordine.setProdotti(List.of(op));

        OrderResponseDTO response = OrdineMapper.toOrderResponse(ordine);
        assertEquals(new BigDecimal("1200.00"), response.getItems().get(0).getPriceAtPurchase());
    }
}
