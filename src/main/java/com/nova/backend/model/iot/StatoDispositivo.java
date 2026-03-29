package com.nova.backend.model.iot;

/**
 * Stati operativi di un dispositivo IoT nel sistema Nova.
 * Utilizzato per il monitoraggio real-time e la diagnostica.
 */
public enum StatoDispositivo {
    ONLINE,      // Dispositivo connesso e operativo
    OFFLINE,     // Dispositivo non raggiungibile
    MANUTENZIONE // Dispositivo in fase di aggiornamento o riparazione
}