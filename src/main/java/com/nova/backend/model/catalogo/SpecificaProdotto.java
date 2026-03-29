package com.nova.backend.model.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Dinamismo del catalogo (Entity-Attribute-Value pattern ridotto).
 * Permette di archiviare metadati non-strutturati dinamici (es. "Risoluzione -> 4K", "Marca -> Samsung").
 */
@Entity
@Table(name = "product_specifications")
@Getter
@Setter
public class SpecificaProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referenza logica al prodotto descritto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    // Nome della metrica (es. "Voltaggio")
    @Column(name = "label", nullable = false, length = 100)
    private String etichetta;

    // Valore della metrica (es. "220V")
    @Column(name = "product_value", nullable = false, length = 255)
    private String valore;

    public SpecificaProdotto() {}

    public SpecificaProdotto(Prodotto prodotto, String etichetta, String valore) {
        this.prodotto = prodotto;
        this.etichetta = etichetta;
        this.valore = valore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecificaProdotto that = (SpecificaProdotto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SpecificaProdotto{" +
                "id=" + id +
                ", prodotto=" + (prodotto != null ? prodotto.getId() : null) +
                ", etichetta='" + etichetta + '\'' +
                ", valore='" + valore + '\'' +
                '}';
    }
}