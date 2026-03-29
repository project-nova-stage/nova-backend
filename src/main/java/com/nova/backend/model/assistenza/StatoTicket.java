package com.nova.backend.model.assistenza;

/**
 * Stato interno di un ticket di supporto.
 */
public enum StatoTicket {
    APERTO,          // Segnalazione appena ricevuta
    IN_CORSO,        // Operatore assegnato
    IN_ATTESA_CLIENTE, // Richiesta di info aggiuntive
    RISOLTO,         // Problema sistemato
    CHIUSO           // Ticket archiviato
}