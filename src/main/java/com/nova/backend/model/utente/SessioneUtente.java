package com.nova.backend.model.utente;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "session")
@Getter
@Setter
public class SessioneUtente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    private Utente idUtente;

    @Column(name="token", nullable=false, length=255, unique=true)
    private String token;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Column(name="expires_at", nullable=false)
    private LocalDateTime expiresAt;

    @Column(name="revoked", nullable=false)
    private Boolean revoked;
}
