package com.nova.backend.model.assistenza;

/**
 * Stati possibili per una fase di installazione fisica sul campo.
 */
public enum StatoInstallazione {
    PROGRAMMATA,  // Intervento fissato a calendario
    IN_CORSO,     // Tecnico sul posto
    COMPLETATA,   // Installazione terminata con successo
    FALLITA,      // Impossibile procedere (es. tetto non idoneo)
    ANNULLATA     // Disdetta dal cliente o azienda
}