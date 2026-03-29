package com.nova.backend.model.assistenza;

import com.nova.backend.model.ordine.Ordine;

/**
 * Stati del ciclo di vita di una richiesta di servizio (lead).
 */
public enum StatoServizio {
    NUOVA,            // Ricevuta
    IN_VALUTAZIONE,   // Analisi preliminare
    SOPRALLUOGO,      // Uscita tecnica
    PREVENTIVO_INVIATO, // Attesa conferma cliente
    ACCETTATA,        // Trasformata in ordine/installazione
    RIFIUTATA         // Lead chiuso senza esito
}