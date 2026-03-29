package com.nova.backend.model.assistenza;

import com.nova.backend.model.utente.Utente;

import com.nova.backend.model.catalogo.Prodotto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Feedback degli utenti sui prodotti acquistati.
 * Include una valutazione numerica e un commento testuale opzionale.
 */
@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    // Punteggio (tipicamente da 1 a 5)
    @Column(name = "rating", nullable = false)
    private Integer valutazione;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String commento;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime dataCreazione;

    public Recensione() {}

    @Override
    public String toString() {
        return "Recensione{" +
                "id=" + id +
                ", valutazione=" + valutazione +
                ", prodotto=" + (prodotto != null ? prodotto.getId() : null) +
                '}';
    }
}