package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

// Revisione: Rinominati campi in italiano ('ordine', 'prodotto', 'quantita', 'prezzoAcquisto') per allineamento regole di progetto.
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
    @JoinColumn(name = "order_id", nullable = false)
    private Ordine ordine;

    //Relazione con Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    @Column(name = "quantity", nullable = false)
    private Integer quantita;

    //Prezzo stabilito al momento dell'acquisto
    @Column(name = "unit_price_snapshot", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoAcquisto;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    //Costruttori
    public OrdineProdotto() {}

    public OrdineProdotto(Ordine ordine, Prodotto prodotto, Integer quantita, BigDecimal prezzoAcquisto) {
        this.ordine = ordine;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.prezzoAcquisto = prezzoAcquisto;
    }

    // Equals allineato ai nuovi campi
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
                ", ordine=" + (ordine != null ? ordine.getId() : null) +
                ", prodotto=" + (prodotto != null ? prodotto.getId() : null) +
                ", quantita=" + quantita +
                ", prezzoAcquisto=" + prezzoAcquisto +
                '}';
    }
}
