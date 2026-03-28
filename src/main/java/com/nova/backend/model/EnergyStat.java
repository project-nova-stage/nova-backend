package com.nova.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "energy_stat")
@Getter
@Setter
public class EnergyStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente idUtente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device idDevice;

    @Column(name = "date", length = 255)
    private Date date;

    @Column(name = "consumption_kwh", length = 255)
    private BigDecimal consumptionKwh;

    //Override
    @Override
    public String toString() {
        return "id" + id +
                " " + "id user" + idUtente +
                " " + " id device " + idDevice +
                " " + "date" + date +
                " " + "consumption_kwh" + consumptionKwh;
    }
}

