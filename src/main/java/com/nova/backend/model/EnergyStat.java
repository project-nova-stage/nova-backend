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
    private Utente user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device device_id;

    @Column(name = "date", length = 255)
    private Date date;

    @Column(name = "consumption_kwh", length = 255)
    private BigDecimal consumption_kwh;

    //Override
    @Override
    public String toString() {
        return "id" + id +
                " " + "id user" + user_id +
                " " + " id device " + device_id +
                " " + "date" + date +
                " " + "consumption_kwh" + consumption_kwh;
    }
}
