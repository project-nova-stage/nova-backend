package com.nova.backend.model.utente;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 * Entità principale che mappa la tabella "users".
 * Centralizza l'anagrafica, i tipi di profilo e le credenziali di accesso.
 * Implementa UserDetails per delegare direttamente a Spring Security la gestione dell'autenticazione.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Email usata anche come username per il login
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    // Hash della password (BCrypt gestito nel Service Layer)
    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false, length = 80)
    private String nome;

    @Column(name = "last_name", nullable = false, length = 80)
    private String cognome;

    // Ruolo principale nel sistema, mappato come stringa nel DB
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Ruolo ruolo;

    // Tipo di cliente aziendale o privato. Campo nullable poiché non applicabile ad ADMIN o TECNICO
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", length = 10)
    private TipoCliente tipoCliente;

    // Data di registrazione generata automaticamente dal framework alla prima persistenza
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    // =========================================
    // Metodi implementati dall'interfaccia UserDetails
    // =========================================

    /**
     * Ritorna i privilegi garantiti all'utente per i controlli di sicurezza.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + ruolo.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Identifica se l'account è disabilitato (utile per soft delete o ban temporanei)
    @Override
    public boolean isEnabled() {
        return true;
    }
}