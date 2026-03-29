package com.nova.backend.model.iot;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * Registro storico degli eventi generati dai dispositivi IoT.
 * Memorizza messaggi di stato, errori e alert tecnici.
 */
@Entity
@Table(name = "device_logs")
@Getter   
@Setter
public class LogDispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dispositivo sorgente dell'evento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Dispositivo dispositivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private TipoEvento tipoEvento;

    @Column(name = "message", length = 255)
    private String messaggio;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public LogDispositivo() {}

    @Override
    public String toString() {
        return "LogDispositivo{" +
                "id=" + id +
                ", tipoEvento=" + tipoEvento +
                ", messaggio='" + messaggio + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}