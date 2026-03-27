package com.nova.backend.model;

import com.nova.backend.model.enums.Ruolo;
import com.nova.backend.model.enums.TipoCliente;
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

@Entity
@Table(name = "users")
@Getter
@Setter
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false, length = 80)
    private String nome;

    @Column(name = "last_name", nullable = false, length = 80)
    private String cognome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Ruolo ruolo;

    // Nullable: ha senso solo se ruolo = CLIENTE
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", length = 10)
    private TipoCliente tipoCliente;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    // =========================================
    // Spring Security — interfaccia UserDetails
    // =========================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + ruolo.name()));
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
