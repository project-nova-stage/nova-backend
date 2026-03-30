package com.nova.backend.model.assistenza;

import com.nova.backend.model.utente.Utente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * Singolo messaggio all'interno di una conversazione di supporto.
 * Può essere inviato sia dal cliente che dall'operatore tecnico.
 */
@Entity
@Table(name = "support_messages")
@Getter
@Setter
@NoArgsConstructor
public class MessaggioSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ticket di riferimento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketSupporto ticket;

    // Autore del messaggio (Cliente o Operatore)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Utente mittente;

    // Contenuto testuale della comunicazione
    @Column(name = "message_content", nullable = false, columnDefinition = "TEXT")
    private String contenuto;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant dataCreazione;

    @Override
    public String toString() {
        return "MessaggioSupporto{" +
                "id=" + id +
                ", contenuto='" + (contenuto != null && contenuto.length() > 20 ? contenuto.substring(0, 20) + "..." : contenuto) + '\'' +
                ", dataCreazione=" + dataCreazione +
                '}';
    }
}