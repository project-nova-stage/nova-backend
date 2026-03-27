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

/**
 * Entità del catalogo referenziata in carrelli e ordini.
 * Racchiude i dati basilari di vendita e lo stato corrente dell'inventario.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Codice identificativo univoco (Stock Keeping Unit)
    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(name = "name", nullable = false, length = 100)
    private String nome;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzo;

    // Quantità a magazzino
    @Column(name = "stock_quantity", nullable = false)
    private Integer quantitaDisponibile;

    // Flag logico per la rimozione del prodotto senza eliminare lo storico degli ordini
    @Column(name = "is_active", nullable = false)
    private Boolean attivo = true;

    // Riferimento alla categoria principale della gerarchia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Categoria categoria;

    // Lista in cascata delle immagini relative al prodotto
    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImmagineProdotto> immagini;

    // Lista in cascata dei dettagli/specifiche tecniche (es. "Colore", "Nero")
    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SpecificaProdotto> specifiche;

    // Mappatura bidirezionale dello storico acquisti (solo lettura/navigazione)
    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdineProdotto> ordini;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Prodotto() {}

    public Prodotto(String sku, String nome, BigDecimal prezzo, Integer quantitaDisponibile, Boolean attivo) {
        this.sku = sku;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantitaDisponibile = quantitaDisponibile;
        this.attivo = attivo != null ? attivo : true;
    }

    // Equals basato unicamente sui campi identificativi di business (id e sku) per coerenza in Set/Collection
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