package com.nova.backend.mapper.ordine;

import com.nova.backend.dto.ordine.response.CartItemDTO;
import com.nova.backend.dto.ordine.response.CartResponseDTO;
import com.nova.backend.dto.ordine.response.OrderItemDTO;
import com.nova.backend.dto.ordine.response.OrderResponseDTO;
import com.nova.backend.model.ordine.Carrello;
import com.nova.backend.model.ordine.CarrelloProdotto;
import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.OrdineProdotto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper ufficiale per il modulo Ordine & Carrello.
 */
public class OrdineMapper {

    public static CartResponseDTO toCartResponse(Carrello carrello) {
        if (carrello == null) return null;

        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(carrello.getId());
        
        List<CartItemDTO> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        if (carrello.getProdotti() != null) {
            for (CarrelloProdotto cp : carrello.getProdotti()) {
                CartItemDTO item = new CartItemDTO();
                item.setProductId(cp.getProdotto().getId());
                item.setProductNome(cp.getProdotto().getNome());
                item.setUnitPrice(cp.getProdotto().getPrezzo());
                item.setQuantity(cp.getQuantita());
                
                BigDecimal subtotal = item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
                item.setSubtotal(subtotal);
                total = total.add(subtotal);
                
                items.add(item);
            }
        }
        
        dto.setItems(items);
        dto.setTotalAmount(total);
        return dto;
    }

    public static OrderResponseDTO toOrderResponse(Ordine ordine) {
        if (ordine == null) return null;

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(ordine.getId());
        dto.setNumeroOrdine(ordine.getNumeroOrdine());
        dto.setStato(ordine.getStato() != null ? ordine.getStato().name() : null);
        dto.setImportoTotale(ordine.getImportoTotale());
        dto.setDataOrdine(ordine.getDataOrdine());

        if (ordine.getProdotti() != null) {
            dto.setItems(ordine.getProdotti().stream()
                    .map(OrdineMapper::toOrderItemDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    private static OrderItemDTO toOrderItemDTO(OrdineProdotto op) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(op.getProdotto().getId());
        dto.setProductNome(op.getProdotto().getNome());
        dto.setQuantity(op.getQuantita());
        dto.setPriceAtPurchase(op.getPrezzoAcquisto());
        return dto;
    }
}
