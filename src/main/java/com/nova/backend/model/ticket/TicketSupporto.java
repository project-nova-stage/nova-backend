package com.nova.backend.model.ticket;

import com.nova.backend.model.Utente;
import com.nova.backend.model.ticket.enums.PrioritaTicket;
import com.nova.backend.model.ticket.enums.StatoTicket;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "support_tickets")
@Getter
@Setter
public class TicketSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    @Column(nullable = false, length = 255)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioritaTicket priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoTicket status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Relazione bi-direzionale con i messaggi del ticket
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessaggioSupporto> messaggi = new ArrayList<>();
}