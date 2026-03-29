package com.nova.backend.model.assistenza;

import com.nova.backend.model.utente.Utente;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestione delle segnalazioni di supporto (Ticketing).
 * Permette il tracciamento di problematiche tecniche o amministrative.
 */
@Entity
@Table(name = "support_tickets")
@Getter
@Setter
public class TicketSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Utente che ha aperto la segnalazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    // Oggetto sintetico del ticket
    @Column(name = "subject", nullable = false, length = 255)
    private String oggetto;

    // Urgenza della segnalazione
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private PrioritaTicket priorita;

    // Fase del ciclo di vita del ticket
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatoTicket stato;

    // Istante di apertura automatizzato
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime dataCreazione;

    // Conversazione interna associata al ticket
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessaggioSupporto> messaggi = new ArrayList<>();

    public TicketSupporto() {}

    @Override
    public String toString() {
        return "TicketSupporto{" +
                "id=" + id +
                ", oggetto='" + oggetto + '\'' +
                ", stato=" + stato +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}