package com.nova.backend.model.utente;

import com.nova.backend.model.ordine.Ordine;

import com.nova.backend.model.ordine.Carrello;

/**
 * Macrostati globali di gestione delle autorizzazioni (RBAC - Role Based Access Control).
 * Mappati dinamicamente nei GrantedAuthority di Spring Security.
 */
public enum Ruolo {
    ADMIN,     // Supervisione globale e CRUD completo
    CLIENTE,   // Utente base con carrello/ordini e permessi confinati ai record proprietari
    TECNICO    // Operatore dashboard lato installazioni / IoT / assistenza
}