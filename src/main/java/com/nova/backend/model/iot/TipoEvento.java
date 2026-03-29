package com.nova.backend.model.iot;

/**
 * Tipologie di eventi telemetrici o diagnostici generati dai dispositivi.
 */
public enum TipoEvento {
    CAMBIO_STATO, // Notifica di passaggio ONLINE/OFFLINE
    ERRORE,       // Anomalie hardware o di comunicazione
    ALIMENTAZIONE // Eventi legati al consumo elettrico o batteria
}