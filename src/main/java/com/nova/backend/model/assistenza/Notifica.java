package com.nova.backend.model.assistenza;

import com.nova.backend.model.utente.Utente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * Sistema di alert e comunicazioni push/in-app per l'utente.
 * Traccia la lettura e il contenuto degli avvisi di sistema.
 */
@Entity
@Table(name = "notifications")
@Getter
@Setter
public class Notifica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Destinatario della notifica
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    @Column(name = "title", nullable = false, length = 100)
    private String titolo;

    @Column(name = "message", nullable = false, length = 500)
    private String messaggio;

    // Flag di avvenuta visualizzazione
    @Column(name = "is_read", nullable = false)
    private Boolean letta = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant dataCreazione;

    public Notifica() {}

    @Override
    public String toString() {
        return "Notifica{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", letta=" + letta +
                '}';
    }
}