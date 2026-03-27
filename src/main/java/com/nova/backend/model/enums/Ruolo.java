package com.nova.backend.model.enums;

/**
 * Macrostati globali di gestione delle autorizzazioni (RBAC - Role Based Access Control).
 * Mappati dinamicamente nei GrantedAuthority di Spring Security.
 */
public enum Ruolo {
    ADMIN,     // Supervisione globale e CRUD completo
    CLIENTE,   // Utente base con carrello/ordini e permessi confinati ai record proprietari
    TECNICO    // Operatore dashboard lato installazioni / IoT / assistenza
}
