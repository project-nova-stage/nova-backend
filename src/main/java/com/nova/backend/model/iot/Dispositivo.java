package com.nova.backend.model.iot;

import com.nova.backend.model.utente.Utente;

import com.nova.backend.model.catalogo.Prodotto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * Rappresenta un'istanza fisica di un prodotto IoT installata presso un utente.
 * Collega l'anagrafica prodotti all'hardware specifico tramite codice e indirizzo MAC.
 */
@Entity
@Table(name = "devices")
@Getter
@Setter
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Proprietario del dispositivo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    // Riferimento al modello di prodotto (es. "Kit Smart Home V1")
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "product_id", nullable = false)
    private Prodotto prodotto;

    // Codice univoco assegnato in fabbrica
    @Column(name = "device_code", unique = true, nullable = false, length = 100)
    private String codiceDispositivo;

    // Indirizzo fisico di rete (Network Interface)
    @Column(name = "mac_address", length = 255)
    private String indirizzoMac;

    // Descrizione testuale della posizione (es. "Soggiorno", "Ripostiglio")
    @Column(name = "location", nullable = false, length = 255)
    private String posizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private StatoDispositivo stato;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Dispositivo() {}

    @Override
    public String toString() {
        return "Dispositivo{" +
                "id=" + id +
                ", codiceDispositivo='" + codiceDispositivo + '\'' +
                ", posizione='" + posizione + '\'' +
                ", stato=" + stato +
                '}';
    }
}