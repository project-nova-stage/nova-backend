# 🚀 PROMPT COMPLETO PER FRONTEND ANGULAR - NEUROHOME ROBOTICS

## 📋 DESCRIZIONE PROGETTO
Crea un'applicazione web Angular completa per NeuroHome Robotics, una startup nel settore domotica e robotica intelligente che serve clienti B2C e B2B.

Il sito deve essere un prototipo production-ready con:
- 10 pagine principali completamente funzionali
- Design futuristico tech-dark con effetti antigravity
- Routing avanzato Angular Router
- Animazioni fluide con Angular Animations + particelle fluttuanti
- Componenti riutilizzabili seguendo best practices Angular
- Completamente responsive (mobile-first)
- Mock data realistici TypeScript
- Servizi modulari per gestione stato e dati

## 🎨 DESIGN SYSTEM
### Palette Colori
- **Primary Dark**: `#0A0F1C` (Background principale)
- **Secondary Dark**: `#111827` (Background UI secondario)
- **Accent Cyan**: `#00E5FF` (Primary accent - neon tech)
- **Accent Purple**: `#7C3AED` (Secondary accent - AI)
- **Text Primary**: `#E5E7EB`
- **Card BG**: `#1F2937` (Glassmorphism base)

### Typography
- **Heading**: 'Orbitron', sans-serif
- **Body**: 'Inter', sans-serif

### Effetti Visuali
1. **Glassmorphism**: Blur 12px, border semi-trasparente.
2. **Glow Effects**: Box-shadow neon per bottoni e card attive.
3. **Antigravity**: Particelle fluttuanti nel background dell'Hero.

## 📂 STRUTTURA PROGETTO
- `core/`: services (cart, product, device, ai, order), models, guards.
- `shared/`: components (navbar, footer, particles, card, button, badge).
- `features/`: home, products, cart, ai-assistant, services, dashboard, about, support.

## 🗺️ ROUTING (10 Pagine)
1. Home (`/`)
2. Prodotti (`/prodotti`)
3. Dettaglio Prodotto (`/prodotti/:id`)
4. Carrello (`/carrello`)
5. AI Assistant (`/ai-assistant`)
6. Servizi (`/servizi`)
7. Dashboard (`/dashboard`)
8. Chi Siamo (`/about`)
9. Supporto (`/supporto`)
10. 404 (`/404`)

## 🛠️ SPECIFICHE TECNICHE
- Angular 18+ (Standalone Components)
- Signals per la gestione dello stato (Cart, UI state)
- Angular Animations (Fade-in staggered, Floating particles)
- Reactive Forms per preventivi e supporto
- Lucide-angular per le icone
