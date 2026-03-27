package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * Line-Item dello scontrino digitale/Ordine.
 * Questa classe de-normalizza il prezzo memorizzandolo staticamente per evitare che
 * i cambiamenti di prezzo successivi del catalogo alterino lo storico contabile.
 */
@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrdineProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Testata ordine di appartenenza
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Ordine ordine;

    // Prodotto target venduto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    // Volumetria venduta per questa riga
    @Column(name = "quantity", nullable = false)
    private Integer quantita;

    // [CRITICAL] Immagine logica del prezzo al momento esatto del checkout persistita in DB
    @Column(name = "unit_price_snapshot", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoAcquisto;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public OrdineProdotto() {}

    public OrdineProdotto(Ordine ordine, Prodotto prodotto, Integer quantita, BigDecimal prezzoAcquisto) {
        this.ordine = ordine;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.prezzoAcquisto = prezzoAcquisto;
    }

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
