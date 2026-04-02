# Documentazione API - Frontend

Questa guida contiene tutte le chiamate API attualmente esposte dal backend, suddivise per modulo, con i relativi esempi
di payload JSON da trasmettere e la struttura precisa dei DTO.

---

## 🛠️ Modulo Assistenza

### Installazioni

**Base URL:** `/api/v1/assistenza/installazioni`

| Metodo    | Endpoint               | Descrizione                                     | Parametri / Query                                   | Esempio JSON Payload da trasmettere                                                                                                                                            |
|:----------|:-----------------------|:------------------------------------------------|:----------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**  | `/`                    | Pianifica una nuova installazione               | -                                                   | `{`<br>&nbsp;&nbsp;`"serviceRequestId": 1,`<br>&nbsp;&nbsp;`"orderId": 42,`<br>&nbsp;&nbsp;`"technicianId": 5,`<br>&nbsp;&nbsp;`"scheduledDate": "2026-04-10T10:00:00"`<br>`}` |
| **GET**   | `/`                    | Ottieni tutte le installazioni                  | -                                                   | -                                                                                                                                                                              |
| **GET**   | `/tecnico/{tecnicoId}` | Ottieni le installazioni assegnate a un tecnico | Path: `tecnicoId`                                   | -                                                                                                                                                                              |
| **PATCH** | `/{id}/stato`          | Aggiorna lo stato di un'installazione           | Path: `id`<br>Query: `stato` (`StatoInstallazione`) | -                                                                                                                                                                              |

### Messaggi Supporto

**Base URL:** `/api/v1/assistenza/messaggi`

| Metodo   | Endpoint             | Descrizione                                    | Parametri / Query | Esempio JSON Payload da trasmettere                                                                                                             |
|:---------|:---------------------|:-----------------------------------------------|:------------------|:------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST** | `/`                  | Invia un nuovo messaggio per un ticket         | -                 | `{`<br>&nbsp;&nbsp;`"ticketId": 10,`<br>&nbsp;&nbsp;`"senderId": 2,`<br>&nbsp;&nbsp;`"content": "Il problema persiste dopo il riavvio."`<br>`}` |
| **GET**  | `/ticket/{ticketId}` | Recupera l'intera cronologia chat di un ticket | Path: `ticketId`  | -                                                                                                                                               |

### Notifiche

**Base URL:** `/api/v1/assistenza/notifiche`

| Metodo    | Endpoint                       | Descrizione                                      | Parametri / Query | Esempio JSON Payload da trasmettere                                                                                                                    |
|:----------|:-------------------------------|:-------------------------------------------------|:------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**  | `/`                            | Crea e invia una notifica (Test/Interno)         | -                 | `{`<br>&nbsp;&nbsp;`"userId": 1,`<br>&nbsp;&nbsp;`"title": "Nuovo Aggiornamento",`<br>&nbsp;&nbsp;`"message": "Il tuo ticket è stato risolto."`<br>`}` |
| **GET**   | `/utente/{utenteId}`           | Ottieni tutte le notifiche di un utente          | Path: `utenteId`  | -                                                                                                                                                      |
| **GET**   | `/utente/{utenteId}/non-lette` | Ottieni solo le notifiche non lette di un utente | Path: `utenteId`  | -                                                                                                                                                      |
| **PATCH** | `/{id}/leggi`                  | Segna una singola notifica come letta            | Path: `id`        | -                                                                                                                                                      |

### Recensioni

**Base URL:** `/api/v1/assistenza/recensioni`

| Metodo     | Endpoint                 | Descrizione                                | Parametri / Query  | Esempio JSON Payload da trasmettere                                                                                                                         |
|:-----------|:-------------------------|:-------------------------------------------|:-------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `/`                      | Crea una nuova recensione                  | -                  | `{`<br>&nbsp;&nbsp;`"userId": 1,`<br>&nbsp;&nbsp;`"productId": 20,`<br>&nbsp;&nbsp;`"rating": 5,`<br>&nbsp;&nbsp;`"comment": "Prodotto eccellente!"`<br>`}` |
| **GET**    | `/prodotto/{prodottoId}` | Ottieni tutte le recensioni di un prodotto | Path: `prodottoId` | -                                                                                                                                                           |
| **DELETE** | `/{id}`                  | Elimina una recensione                     | Path: `id`         | -                                                                                                                                                           |

### Richieste Servizio

**Base URL:** `/api/v1/assistenza/richieste`

| Metodo    | Endpoint             | Descrizione                                 | Parametri / Query                              | Esempio JSON Payload da trasmettere                                                                                                                  |
|:----------|:---------------------|:--------------------------------------------|:-----------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**  | `/`                  | Crea una nuova richiesta di servizio        | -                                              | `{`<br>&nbsp;&nbsp;`"userId": 1,`<br>&nbsp;&nbsp;`"projectType": "DOMOTICA",`<br>&nbsp;&nbsp;`"description": "Installazione pannelli solari"`<br>`}` |
| **GET**   | `/`                  | Ottieni tutte le richieste di servizio      | -                                              | -                                                                                                                                                    |
| **GET**   | `/{id}`              | Ottieni una singola richiesta per ID        | Path: `id`                                     | -                                                                                                                                                    |
| **GET**   | `/utente/{utenteId}` | Ottieni le richieste di un utente specifico | Path: `utenteId`                               | -                                                                                                                                                    |
| **PATCH** | `/{id}/stato`        | Aggiorna lo stato della pratica             | Path: `id`<br>Query: `stato` (`StatoServizio`) | -                                                                                                                                                    |

### Ticket Supporto

**Base URL:** `/api/v1/assistenza/ticket`

| Metodo     | Endpoint | Descrizione                        | Parametri / Query | Esempio JSON Payload da trasmettere                                                                                         |
|:-----------|:---------|:-----------------------------------|:------------------|:----------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `/`      | Crea un nuovo ticket di supporto   | -                 | `{`<br>&nbsp;&nbsp;`"userId": 1,`<br>&nbsp;&nbsp;`"subject": "Sensore offline",`<br>&nbsp;&nbsp;`"priority": "ALTA"`<br>`}` |
| **GET**    | `/`      | Ottieni tutti i ticket             | -                 | -                                                                                                                           |
| **GET**    | `/{id}`  | Ottieni un ticket specifico per ID | Path: `id`        | -                                                                                                                           |
| **PUT**    | `/{id}`  | Aggiorna un ticket esistente       | Path: `id`        | *Stesso payload della POST*                                                                                                 |
| **DELETE** | `/{id}`  | Elimina un ticket                  | Path: `id`        | -                                                                                                                           |

#### 📐 Struttura Precisa DTO - Assistenza (Richieste)

* **InstallazioneRequest**: `serviceRequestId` (Long, Obbligatorio), `orderId` (Long, Opzionale), `technicianId` (Long,
  Obbligatorio), `scheduledDate` (LocalDateTime, Obbligatorio e Futuro).
* **MessaggioSupportoRequest**: `ticketId` (Long), `senderId` (Long), `content` (String).
* **NotificaRequest**: `userId` (Long), `title` (String), `message` (String).
* **RecensioneRequest**: `userId` (Long), `productId` (Long), `rating` (Integer), `comment` (String).
* **RichiestaServizioRequest**: `userId` (Long, Obbligatorio), `projectType` (Enum/String, Obbligatorio),
  `description` (String, Obbligatorio).
* **TicketSupportoRequest**: `userId` (Long, Obbligatorio), `subject` (String, Obbligatorio), `priority` (Enum/String,
  Obbligatorio).

---

## 📦 Modulo Catalogo

*(Nota: per le rotte del catalogo è abilitato il CORS per tutte le origini `*`)*

### Categoria

**Base URL:** `/api/v1/catalogo/categoria`

| Metodo     | Endpoint | Descrizione                      | Parametri / Query | Esempio JSON Payload da trasmettere                                                   |
|:-----------|:---------|:---------------------------------|:------------------|:--------------------------------------------------------------------------------------|
| **POST**   | `/`      | Crea una nuova categoria         | -                 | `{`<br>&nbsp;&nbsp;`"nome": "Smart Home"`<br>`}`                                      |
| **GET**    | `/`      | Ottieni tutte le categorie       | -                 | -                                                                                     |
| **GET**    | `/{id}`  | Ottieni una categoria per ID     | Path: `id`        | -                                                                                     |
| **PUT**    | `/{id}`  | Aggiorna una categoria esistente | Path: `id`        | `{`<br>&nbsp;&nbsp;`"id": 1,`<br>&nbsp;&nbsp;`"nome": "Smart Home Aggiornato"`<br>`}` |
| **DELETE** | `/{id}`  | Elimina una categoria            | Path: `id`        | -                                                                                     |

### Prodotto

**Base URL:** `/api/v1/catalogo/prodotto`

| Metodo     | Endpoint     | Descrizione                            | Parametri / Query | Esempio JSON Payload da trasmettere                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         |
|:-----------|:-------------|:---------------------------------------|:------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `/`          | Crea un nuovo prodotto                 | -                 | `{`<br>&nbsp;&nbsp;`"nome": "Sensore Movimento",`<br>&nbsp;&nbsp;`"descrizione": "Sensore Wi-Fi",`<br>&nbsp;&nbsp;`"prezzo": 29.99,`<br>&nbsp;&nbsp;`"sku": "SENS-MOV-01",`<br>&nbsp;&nbsp;`"quantitaDisponibile": 100,`<br>&nbsp;&nbsp;`"categoriaId": 1,`<br>&nbsp;&nbsp;`"attivo": true,`<br>&nbsp;&nbsp;`"immagini": [`<br>&nbsp;&nbsp;&nbsp;&nbsp;`{ "urlImmagine": "url1.jpg", "principale": true }`<br>&nbsp;&nbsp`],`<br>&nbsp;&nbsp;`"specifiche": [`<br>&nbsp;&nbsp;&nbsp;&nbsp;`{ "chiave": "Connettività", "valore": "Wi-Fi 2.4GHz" }`<br>&nbsp;&nbsp`]`<br>`}` |
| **GET**    | `/`          | Ottieni tutti i prodotti attivi        | -                 | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **GET**    | `/sku/{sku}` | Ottieni un prodotto tramite il suo SKU | Path: `sku`       | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **GET**    | `/{id}`      | Ottieni un prodotto per ID             | Path: `id`        | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
| **PUT**    | `/{id}`      | Aggiorna un prodotto esistente         | Path: `id`        | *Stesso payload della POST*                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| **DELETE** | `/{id}`      | Elimina un prodotto                    | Path: `id`        | -                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |

#### 📐 Struttura Precisa DTO - Catalogo (Richieste)

* **CategoriaDTO**: `id` (Long, Opzionale per POST), `nome` (String, Obbligatorio).
* **ProdottoRequestDTO**: `nome` (String, Obbligatorio), `descrizione` (String, Opzionale), `prezzo` (BigDecimal,
  Obbligatorio > 0.0), `sku` (String, Obbligatorio), `quantitaDisponibile` (Integer), `categoriaId` (Long,
  Obbligatorio), `attivo` (Boolean), `immagini` (Array di `ImmagineProdottoDTO`), `specifiche` (Array di
  `SpecificaProdottoDTO`).
* **ImmagineProdottoDTO**: `id` (Long, Opzionale), `urlImmagine` (String), `principale` (Boolean).
* **SpecificaProdottoDTO**: `id` (Long, Opzionale), `chiave` (String), `valore` (String).

---

## 🛒 Modulo Ordini

### Ordini

**Base URL:** `/api/v1/ordini`

| Metodo     | Endpoint | Descrizione                            | Parametri / Query                        | Esempio JSON Payload da trasmettere                                                                                                                                                                                                                                                                                      |
|:-----------|:---------|:---------------------------------------|:-----------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `/`      | Crea un nuovo ordine                   | -                                        | `{`<br>&nbsp;&nbsp;`"utenteId": 1,`<br>&nbsp;&nbsp;`"numeroOrdine": "ORD-12345",`<br>&nbsp;&nbsp;`"stato": "IN_ELABORAZIONE",`<br>&nbsp;&nbsp;`"importoTotale": 59.98,`<br>&nbsp;&nbsp;`"prodotti": [`<br>&nbsp;&nbsp;&nbsp;&nbsp;`{ "prodottoId": 2, "quantita": 2, "prezzoAcquisto": 29.99 }`<br>&nbsp;&nbsp`]`<br>`}` |
| **GET**    | `/{id}`  | Ottieni i dettagli di un ordine per ID | Path: `id`                               | -                                                                                                                                                                                                                                                                                                                        |
| **GET**    | `/`      | Lista ordini (con filtri opzionali)    | Query *(opzionali)*: `utenteId`, `stato` | -                                                                                                                                                                                                                                                                                                                        |
| **PUT**    | `/{id}`  | Aggiorna un ordine esistente           | Path: `id`                               | *Stesso payload della POST (con ID valorizzati)*                                                                                                                                                                                                                                                                         |
| **DELETE** | `/{id}`  | Elimina un ordine                      | Path: `id`                               | -                                                                                                                                                                                                                                                                                                                        |

#### 📐 Struttura Precisa DTO - Ordini (Richieste)

* **OrdineDTO**: `id` (Long, Opzionale per POST), `utenteId` (Long), `numeroOrdine` (String), `stato` (Enum/String: es.
  IN_ELABORAZIONE), `importoTotale` (BigDecimal), `dataOrdine` (Instant ISO 8601), `prodotti` (Array di
  `OrdineProdottoDTO`).
* **OrdineProdottoDTO**: `id` (Long, Opzionale), `prodottoId` (Long), `nomeProdotto` (String, Opzionale in POST),
  `quantita` (Integer), `prezzoAcquisto` (BigDecimal).

---

## 👤 Modulo Utente

*(Nota: Questo controller non espone `/api/v1/` ma parte direttamente dalla root. CORS abilitato `*`)*

**Base URL:** `/utente`

| Metodo   | Endpoint            | Descrizione                                | Parametri / Headers                  | Esempio JSON Payload da trasmettere                                                                                                                                                                                                                                |
|:---------|:--------------------|:-------------------------------------------|:-------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST** | `/registrazione`    | Registra un nuovo account                  | -                                    | `{`<br>&nbsp;&nbsp;`"email": "mario.rossi@email.com",`<br>&nbsp;&nbsp;`"password": "Password123!",`<br>&nbsp;&nbsp;`"nome": "Mario",`<br>&nbsp;&nbsp;`"cognome": "Rossi",`<br>&nbsp;&nbsp;`"codiceRuolo": "CLIENTE",`<br>&nbsp;&nbsp;`"tipoCliente": "B2C"`<br>`}` |
| **GET**  | `/getAll`           | Ottieni la lista di tutti gli utenti       | -                                    | -                                                                                                                                                                                                                                                                  |
| **POST** | `/login`            | Autenticazione utente                      | -                                    | `{`<br>&nbsp;&nbsp;`"email": "mario.rossi@email.com",`<br>&nbsp;&nbsp;`"password": "Password123!"`<br>`}`                                                                                                                                                          |
| **POST** | `/logout`           | Invalida la sessione attiva                | Headers: `X-Auth-Token`, `X-User-Id` | -                                                                                                                                                                                                                                                                  |
| **POST** | `/validazioneToken` | Controlla validità token per rotte private | Headers: `X-Auth-Token`, `X-User-Id` | -                                                                                                                                                                                                                                                                  |

#### 📐 Struttura Precisa DTO - Utente (Richieste)

* **RegistroUtenteDTO**: `email` (String, Obbligatorio, Formato Email), `password` (String, Obbligatorio, Min 8
  caratteri), `nome` (String, Obbligatorio), `cognome` (String, Obbligatorio), `codiceRuolo` (String, Opzionale,
  default: CLIENTE), `tipoCliente` (String, Opzionale, B2C/B2B).
* **LoginRequestDTO**: `email` (String, Obbligatorio, Formato Email), `password` (String, Obbligatorio).