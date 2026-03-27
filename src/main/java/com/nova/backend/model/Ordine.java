package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.nova.backend.model.enums.StatoOrdine;

// Revisione: Rinominati campi in italiano (es. 'utente', 'numeroOrdine', 'stato') per allineamento regole di progetto.
@Entity
@Table(name = "orders") 
@Getter
@Setter
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relazione con User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    @Column(name = "order_number", nullable = false, unique = true, length = 50)
    private String numeroOrdine;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatoOrdine stato;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal importoTotale;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime dataOrdine;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdineProdotto> prodotti;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    //Costruttori
    public Ordine() {}

    public Ordine(Utente utente, String numeroOrdine, StatoOrdine stato, BigDecimal importoTotale, LocalDateTime dataOrdine) {
        this.utente = utente;
        this.numeroOrdine = numeroOrdine;
        this.stato = stato;
        this.importoTotale = importoTotale;
        this.dataOrdine = dataOrdine;
    }

    // Equals allineato ai nuovi campi
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return Objects.equals(id, ordine.id) && Objects.equals(numeroOrdine, ordine.numeroOrdine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroOrdine);
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "id=" + id +
                ", utente=" + (utente != null ? utente.getId() : null) +
                ", numeroOrdine='" + numeroOrdine + '\'' +
                ", stato=" + stato +
                ", importoTotale=" + importoTotale +
                ", dataOrdine=" + dataOrdine +
                '}';
    }
}
