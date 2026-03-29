package com.nova.backend.model.ordine;

/**
 * Gestione dello stato di vita (State Machine base) dell'ordine.
 * Sincronizzato con il frontend tramite chiavi italiane autoesplicative.
 */
public enum StatoOrdine {
    IN_ATTESA,    // (PENDING)   Creazione scontrino, iterazione carrello o checkout sospeso
    PAGATO,       // (PAID)      Gateways bancario/API accreditato correttamente
    SPEDITO,      // (SHIPPED)   Logistica ha affifato vettore terza parte
    CONSEGNATO,   // (DELIVERED) End-Of-Line: tracking validato
    ANNULLATO     // (CANCELLED) Chargeback, disdetta cliente o inventory fault
}