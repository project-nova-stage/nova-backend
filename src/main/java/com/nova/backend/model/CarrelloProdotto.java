package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.Instant;
import java.util.Objects;

/**
 * Entità che rappresenta un item nel carrello di un utente,
 * collegando un prodotto al carrello con la quantità selezionata.
 */
// Revisione: Rinominati campi 'carrello', 'prodotto', 'quantita' per allineamento regole di progetto.
@Entity
@Table(
    name = "cart_items",
    uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "product_id"})
)
@Getter
@Setter
public class CarrelloProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Relazione con Cart
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Carrello carrello;

    // 🔗 Relazione con Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    @Column(name = "quantity", nullable = false)
    private Integer quantita;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    //Costruttori
    public CarrelloProdotto() {}

    public CarrelloProdotto(Carrello carrello, Prodotto prodotto, Integer quantita) {
        this.carrello = carrello;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    //in questa parte mi sono fatto aiutare con IA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrelloProdotto that = (CarrelloProdotto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarrelloProdotto{" +
                "id=" + id +
                ", carrello=" + (carrello != null ? carrello.getId() : null) +
                ", prodotto=" + (prodotto != null ? prodotto.getId() : null) +
                ", quantita=" + quantita +
                '}';
    }
}
