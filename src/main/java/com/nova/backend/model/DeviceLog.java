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
    private Device device_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType event_type;

    @Column(name = "message", length = 255)
    private String message;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false ,length = 255)
    private Instant created_at;

    //Override
    @Override
    public String toString() {
        return "id" + id +
                " " + "id device" + device_id +
                " " + " event_type " + event_type +
                " " + "message" + message;
    }
}
