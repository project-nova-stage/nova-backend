package com.nova.backend.model.assistenza;

/**
 * Definisce l'urgenza di un ticket di supporto.
 * Utilizzato per dare priorità agli interventi degli operatori.
 */
public enum PrioritaTicket {
    BASSA,
    MEDIA,
    ALTA,
    URGENTE
}