package com.nova.backend.model.ticket;

import com.nova.backend.model.Utente;
import com.nova.backend.model.ticket.enums.StatoServizio;
import com.nova.backend.model.ticket.enums.TipoProgetto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_requests")
@Getter
@Setter
public class RichiestaServizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_type", nullable = false)
    private TipoProgetto projectType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoServizio status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}