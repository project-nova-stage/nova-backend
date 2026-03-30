package com.nova.backend.model.assistenza;

import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.utente.Utente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entità per la gestione degli interventi tecnici sul campo.
 * Registra la pianificazione e lo stato delle installazioni hardware presso i clienti.
 */
@Entity
@Table(name = "installations")
@Getter
@Setter
@NoArgsConstructor
public class Installazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Richiesta di servizio che ha originato l'installazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_id", nullable = false)
    private RichiestaServizio richiestaServizio;

    // Ordine di acquisto correlato (opzionale se l'installazione è legata a una vendita)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Ordine ordine;

    // Tecnico assegnato all'intervento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id")
    private Utente tecnico;

    // Data e ora pianificata per l'intervento
    @Column(name = "scheduled_date")
    private LocalDateTime dataPianificata;

    // Stato attuale dell'intervento tecnico
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatoInstallazione stato;

    @Override
    public String toString() {
        return "Installazione{" +
                "id=" + id +
                ", dataPianificata=" + dataPianificata +
                ", stato=" + stato +
                '}';
    }
}