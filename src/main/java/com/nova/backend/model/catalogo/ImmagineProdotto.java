package com.nova.backend.model.catalogo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Entità per la gestione asincrona e disaccoppiata dei media associati al listino.
 * Ogni prodotto possiede una N-upla di immagini di cui 1 marcata solitamente come principale (Hero Image).
 */
@Entity
@Table(name = "product_images")
@Getter
@Setter
public class ImmagineProdotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relatività col target Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    // URI (S3, CDN o local path) del file media
    @Column(name = "image_url", nullable = false, length = 500)
    private String urlImmagine;

    // Definizione di priorità per la copertina dell'articolo nella UI
    @Column(name = "is_primary", nullable = false)
    private Boolean principale = false;

    public ImmagineProdotto() {}

    public ImmagineProdotto(Prodotto prodotto, String urlImmagine, Boolean principale) {
        this.prodotto = prodotto;
        this.urlImmagine = urlImmagine;
        this.principale = principale != null ? principale : false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmagineProdotto that = (ImmagineProdotto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ImmagineProdotto{" +
                "id=" + id +
                ", prodotto=" + (prodotto != null ? prodotto.getId() : null) +
                ", urlImmagine='" + urlImmagine + '\'' +
                ", principale=" + principale +
                '}';
    }
}