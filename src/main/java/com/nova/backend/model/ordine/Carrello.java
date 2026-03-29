package com.nova.backend.model.ordine;

import com.nova.backend.model.utente.Utente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Entità aggregatrice temporanea per gli acquisti non ancora finalizzati.
 * Mantiene un legame univoco e stretto (OneToOne) con l'utente proprietario.
 */
@Entity
@Table(name = "carts")
@Getter
@Setter
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relazione univoca e indissolubile con l'utente (Un utente -> Un carrello attivo)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Utente utente;

    // Istante esatto in cui il carrello è stato istanziato al primo accesso
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Aggiornato in automatico da Hibernate a ogni modifica degli elementi interni
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    // Elementi contenuti nel carrello, gestiti in composizione forte (CascadeType.ALL)
    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrelloProdotto> prodotti;

    public Carrello() {}

    public Carrello(Utente utente) {
        this.utente = utente;
    }

    // Equals basato strettamente sull'identificativo tecnico o logiche surrogate (qui su ID per semplicità)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrello carrello = (Carrello) o;
        return Objects.equals(id, carrello.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Carrello{" +
                "id=" + id +
                ", utente=" + (utente != null ? utente.getId() : null) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}