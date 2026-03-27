package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

// Revisione: Rinominati campi in italiano ('nome', 'prezzo', 'quantitaDisponibile', 'attivo') per allineamento regole di progetto.
@Entity
@Table(name = "products")
@Getter
@Setter
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(name = "name", nullable = false, length = 100)
    private String nome;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzo;

    @Column(name = "stock_quantity", nullable = false)
    private Integer quantitaDisponibile;

    @Column(name = "is_active", nullable = false)
    private Boolean attivo = true;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdineProdotto> ordini;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Costruttori
    public Prodotto() {}

    public Prodotto(String sku, String nome, BigDecimal prezzo, Integer quantitaDisponibile, Boolean attivo) {
        this.sku = sku;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantitaDisponibile = quantitaDisponibile;
        this.attivo = attivo != null ? attivo : true;
    }

    // Equals e HashCode allineati ai nuovi campi
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return Objects.equals(id, prodotto.id) && Objects.equals(sku, prodotto.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sku);
    }

    @Override
    public String toString() {
        return "Prodotto{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", nome='" + nome + '\'' +
                ", prezzo=" + prezzo +
                ", quantitaDisponibile=" + quantitaDisponibile +
                ", attivo=" + attivo +
                '}';
    }
}