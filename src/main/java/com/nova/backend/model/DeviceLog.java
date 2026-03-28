package com.nova.backend.model;

import com.nova.backend.model.enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "deviceLog")
@Getter   
@Setter
public class DeviceLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device idDevice;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(name = "message", length = 255)
    private String message;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false ,length = 255)
    private Instant createdAt;

    //Override
    @Override
    public String toString() {
        return "id" + id +
                " " + "id device" + idDevice +
                " " + " event_type " + eventType +
                " " + "message" + message +
                " " + "created_at" + createdAt;
    }
}

