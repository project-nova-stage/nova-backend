package com.nova.backend.model;

import com.nova.backend.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "device")
@Getter
@Setter
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utente idUtente;

    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto idProdotto;

    @Column(name = "device_code", unique = true, nullable = false, length = 100)
    private String deviceCode;

    @Column(name = "mac_address", length = 255)
    private String macAddress;

    @Column(name = "location",nullable = false, length = 255)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;

    //Override
    @Override
    public String toString() {
        return "id" + id + " " + "id utente" + idUtente +
                " " + "id prodotto"+ idProdotto +
                " " + "deviceCode" + deviceCode +
                " " + "macAddress" + macAddress +
                " " + "location" + location +
                " " + "status" + status;
    }

}

