package com.nova.backend.model.assistenza;

import com.nova.backend.model.utente.Utente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * Entità per la gestione delle richieste di sopralluogo o preventivo.
 * Rappresenta l'interesse iniziale dell'utente per un servizio Nova (es. solare, domotica).
 */
@Entity
@Table(name = "service_requests")
@Getter
@Setter
@NoArgsConstructor
public class RichiestaServizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Utente che richiede il servizio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    // Tipologia di progetto richiesto (Impianto fotovoltaico, Smart Home, ecc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "project_type", nullable = false)
    private TipoProgetto tipoProgetto;

    // Descrizione dei dettagli o note aggiuntive
    @Column(name = "description", length = 1000)
    private String descrizione;

    // Stato di avanzamento della richiesta (Aperta, In Lavorazione, ecc.)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatoServizio stato;

    // Data di inserimento del lead
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant dataCreazione;

    @Override
    public String toString() {
        return "RichiestaServizio{" +
                "id=" + id +
                ", tipoProgetto=" + tipoProgetto +
                ", stato=" + stato +
                '}';
    }
}