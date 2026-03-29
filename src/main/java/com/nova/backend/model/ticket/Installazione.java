package com.nova.backend.model.ticket;

import com.nova.backend.model.Ordine;
import com.nova.backend.model.Utente;
import com.nova.backend.model.ticket.enums.StatoInstallazione;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "installations")
@Getter
@Setter
public class Installazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_id", nullable = false)
    private RichiestaServizio richiestaServizio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Ordine ordine; // Opzionale

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_id")
    private Utente tecnico; // L'utente con ruolo TECHNICIAN che effettua il lavoro

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoInstallazione status;
}