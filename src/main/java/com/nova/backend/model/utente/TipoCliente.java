package com.nova.backend.model.utente;

/**
 * Identifica la tipologia di clientela su livello Business. 
 * Utile per applicare listini iva differenti o scontistiche di magazzino.
 */
public enum TipoCliente {
    B2C, // Business to Consumer (Privati)
    B2B  // Business to Business (Aziende con P.IVA)
}