package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrdineProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relazione con Order
    @ManyToOne(fetch = FetchType.LAZY)
    private Ordine order;

    //Relazione con Product
    @ManyToOne(fetch = FetchType.LAZY)
    private Prodotto product;

    @Column(nullable = false)
    private Integer quantity;

    //Prezzo stabilito al momento dell'acquisto
    @Column(name = "unit_price_snapshot", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPriceSnapshot;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    //Costruttori
    public OrdineProdotto() {}

    public OrdineProdotto(Ordine order, Prodotto product, Integer quantity, BigDecimal unitPriceSnapshot) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.unitPriceSnapshot = unitPriceSnapshot;
    }
    //in questa parte mi sono fatto aiutare con IA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdineProdotto that = (OrdineProdotto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OrdineProdotto{" +
                "id=" + id +
                ", order=" + (order != null ? order.getId() : null) +
                ", product=" + (product != null ? product.getId() : null) +
                ", quantity=" + quantity +
                ", unitPriceSnapshot=" + unitPriceSnapshot +
                '}';
    }
}
