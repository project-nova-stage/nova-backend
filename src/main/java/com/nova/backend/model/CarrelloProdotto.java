package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

/**
 * Entità ponte tra il Cart e il Product. 
 * Rappresenta la riga singola del carrello (line-item) salvando la quantità selezionata.
 * Implementa una UniqueConstraint per scongiurare dati duplicati in tabella.
 */
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

    // 🔗 Relazione gerarchica col Padre (Carrello)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Carrello carrello;

    // 🔗 Relazione di referenza al Catalogo (Prodotto fisico o logico)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    // Molteplicità dell'oggetto in riga
    @Column(name = "quantity", nullable = false)
    private Integer quantita;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public CarrelloProdotto() {}

    public CarrelloProdotto(Carrello carrello, Prodotto prodotto, Integer quantita) {
        this.carrello = carrello;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    // Costrutto standard per set mapping ed evitare collisioni nei detach
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
