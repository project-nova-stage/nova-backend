package com.nova.backend.model.iot;

import com.nova.backend.model.utente.Utente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * Entità per la gestione delle regole di automazione domotica.
 * Consente all'utente di definire azioni automatiche (es. "Accendi luci al tramonto")
 * associate a sensori o attuatori specifici.
 */
@Entity
@Table(name = "automations")
@Getter
@Setter
public class Automazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Proprietario della regola
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    // Dispositivo target dell'automazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Dispositivo dispositivo;

    // Azione da eseguire (es. "VALVOLA_OFF", "LED_ON")
    @Column(name = "action", nullable = false, length = 100)
    private String azione;

    // Logica condizionale (es. "HUMIDITY > 80")
    @Column(name = "condition_logic", length = 255)
    private String condizione;

    // Flag di abilitazione della regola
    @Column(name = "is_active", nullable = false)
    private Boolean attiva = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Automazione() {}

    @Override
    public String toString() {
        return "Automazione{" +
                "id=" + id +
                ", azione='" + azione + '\'' +
                ", attiva=" + attiva +
                '}';
    }
}