package com.nova.backend.model.iot;

import com.nova.backend.model.utente.Utente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * Dati storici sui consumi energetici per singolo dispositivo.
 * Utilizzato per reportistica e analisi dei costi.
 */
@Entity
@Table(name = "energy_stats")
@Getter
@Setter
public class StatisticaEnergia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Utente intestatario della bolletta/statistica
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    // Dispositivo responsabile del consumo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Dispositivo dispositivo;

    // Data di riferimento della misurazione
    @Column(name = "stat_date", nullable = false)
    private LocalDate data;

    // Consumo espresso in chilowattora
    @Column(name = "consumption_kwh", nullable = false, precision = 10, scale = 4)
    private BigDecimal consumoKwh;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public StatisticaEnergia() {}

    @Override
    public String toString() {
        return "StatisticaEnergia{" +
                "id=" + id +
                ", data=" + data +
                ", consumoKwh=" + consumoKwh +
                '}';
    }
}