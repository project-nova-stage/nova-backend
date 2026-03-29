# 🗺️ Roadmap di Sviluppo — Nova Backend

Questo documento traccia l'avanzamento dei lavori per il backend del progetto Nova. 
Gli step sono divisi in **Cicli logici** (moduli funzionali) e in **Fasi tecniche**.

---

## 🔵 FASE 1 — Modelli Dati (JPA Entities)
Creazione della struttura del database e mappatura delle classi entity Java.

### 🔹 Ciclo 1 — Autenticazione & Identità
Gestione dei profili utente e permessi base.
- **[Fatto]** `enums/Ruolo.java`: Definizione dei ruoli (ADMIN, CLIENTE, TECNICO).
- **[Fatto]** `enums/TipoCliente.java`: Distinzione B2C/B2B.
- **[Fatto]** `model/Utente.java`: Anagrafica base, password e integrazione Spring Security.

### 🔹 Ciclo 2 — Base Catalogo
Infrastruttura per i prodotti e categorie.
- **[Fatto]** `model/Categoria.java`: Albero gerarchico di categorie (auto-referenziale).
- **[Fatto]** `model/Prodotto.java`: Dati base inventario e gestione del prezzo.
- **[Fatto]** `model/ImmagineProdotto.java`: Gestione galleria media.
- **[Fatto]** `model/SpecificaProdotto.java`: Metadati flessibili e attributi tecnici.

### 🔹 Ciclo 3 — Carrello & Ordini
E-commerce engine per il checkout utente.
- **[Fatto]** `model/Carrello.java` & `model/CarrelloProdotto.java`: Gestione sessione acquisto attiva.
- **[Fatto]** `enums/StatoOrdine.java`: Ciclo di vita ordine (In Attesa, Pagato, ecc.).
- **[Fatto]** `model/Ordine.java` & `model/OrdineProdotto.java`: Storicizzazione acquisti e blocco dei prezzi al checkout.

### 🔹 Ciclo 4 — IoT & Automazione
Integrazione con dispositivi smart e domotica.
- **[Fatto]** `enums/StatoDispositivo.java`, `enums/TipoEvento.java`: Logging base IoT.
- **[Fatto]** `model/Dispositivo.java`: Registrazione hardware associato all'utente.
- **[Fatto]** `model/LogDispositivo.java`: Eventi telemetrici inviati dal dispositivo.
- **[Fatto]** `model/StatisticaEnergia.java`: Aggregazione consumi mensili/giornalieri.
- **[Fatto]** `model/Automazione.java`: Regole condizionali utente per IoT.

### 🔹 Ciclo 5 — Assistenza Clienti
Ticketing e servizi d'installazione sul campo.
- **[Fatto]** `model/RichiestaServizio.java`, `model/TicketSupporto.java`, `model/MessaggioSupporto.java`: Ticketing post-vendita.
- **[Fatto]** `model/Installazione.java`: Richiesta intervento tecnico in loco.
- **[Fatto]** Enum associati (`TipoProgetto`, `StatoServizio`, `StatoInstallazione`, `PrioritaTicket`, `StatoTicket`).
- **[Fatto]** `model/Notifica.java`: Engine per avvisi push ed email.
- **[Fatto]** `model/Recensione.java`: Feedback per l'intero sistema nova.

---

## 🟡 FASE 2 — Livello Dati (Repository Base)
Query JPA per estrarre e manipolare le entità prodotte finora.
- **[Da fare]** `UserRepository`
- **[Da fare]** `CategoryRepository`, `ProductRepository`
- **[Da fare]** `CartRepository`, `OrderRepository`
- **[Da fare]** Livello Data per IoT (`DeviceRepository`, `AutomationRepository`) e Supporto (`SupportTicketRepository`...)

---

## 🟠 FASE 3 — Scambio Dati (DTO & Mapper)
Disaccoppiamento totale dal database e sanificazione con @Valid.
- **[Da fare]** DTO per Auth (`RegisterRequest`, `LoginRequest`).
- **[Da fare]** DTO per Catalogo (`ProductRequest`, `ProductResponse`).
- **[Da fare]** DTO per Checkout (`CartResponse`, `OrderRequest`, `OrderResponse`).
- **[Da fare]** Istanziamento dei vari Mapper (`UserMapper`, `ProductMapper`...).

---

## 🔴 FASE 4 — Logica Applicativa (Service Layer)
Cervello del sistema (`@Transactional`), eccezioni e regole di business.
- **[Da fare]** `UserService` (registrazione sicura via BCrypt, ruoli).
- **[Da fare]** `ProductService`, `CategoryService`.
- **[Da fare]** `CartService` (gestione carrello utente), `OrderService` (evasione checkout).
- **[Da fare]** `DeviceService` per il poller e messaggistica MQTT/REST.

---

## 🟢 FASE 5 — Esposizione API (Controller REST)
Creazione degli endpoint consumabili dai dev Frontend/Mobile.
- **[Da fare]** `AuthController` (POST `/api/v1/auth/register`, `/login`).
- **[Da fare]** `ProductController` (GET catalogo publico, POST per gli Admin).
- **[Da fare]** Rotte protette `CartController`, `OrderController`.
- **[Da fare]** Rotte IoT/Supporto `DeviceController`, `SupportController`.

---

## 🔷 FASE 6 — Sicurezza Globale
Chiusura dei varchi e implementazione Token JWT.
- **[Da fare]** Configurazione filter chain e cors (`SecurityConfig`).
- **[Da fare]** Firma e parsing token (`JwtUtil`, `JwtFilter`).
- **[Da fare]** Protezione rotte tramite annotation (es: `@PreAuthorize("hasAuthority('ROLE_ADMIN')")`).
