package com.nova.backend.config;

import com.nova.backend.model.assistenza.Installazione;
import com.nova.backend.model.assistenza.RichiestaServizio;
import com.nova.backend.model.assistenza.StatoInstallazione;
import com.nova.backend.model.assistenza.StatoServizio;
import com.nova.backend.model.assistenza.TipoProgetto;
import com.nova.backend.model.catalogo.Categoria;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.model.utente.Ruolo;
import com.nova.backend.model.utente.Utente;
import com.nova.backend.repository.assistenza.InstallazioneRepository;
import com.nova.backend.repository.assistenza.RichiestaServizioRepository;
import com.nova.backend.repository.catalogo.CategoriaRepository;
import com.nova.backend.repository.catalogo.ProdottoRepository;
import com.nova.backend.repository.utente.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Il DataSeeder popola entità fittizie a fine didattico o setup per Admin, Clienti, Tecnici
 * e inietta un catalogo base con interventi e installazioni simulate.
 * Utile allo showcase della dashboard. Implementa CommandLineRunner per trigger all'avvio.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UtenteRepository utenteRepository;
    private final ProdottoRepository prodottoRepository;
    private final CategoriaRepository categoriaRepository;
    private final RichiestaServizioRepository richiestaServizioRepository;
    private final InstallazioneRepository installazioneRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UtenteRepository utenteRepository,
                      ProdottoRepository prodottoRepository,
                      CategoriaRepository categoriaRepository,
                      RichiestaServizioRepository richiestaServizioRepository,
                      InstallazioneRepository installazioneRepository,
                      PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.categoriaRepository = categoriaRepository;
        this.richiestaServizioRepository = richiestaServizioRepository;
        this.installazioneRepository = installazioneRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Crea un utente ADMIN di base se non esiste
        Utente admin = utenteRepository.findByEmail("admin@domoticlaboratories.com").orElseGet(() -> {
            Utente u = new Utente();
            u.setEmail("admin@domoticlaboratories.com");
            u.setPassword(passwordEncoder.encode("admin"));
            u.setNome("Admin");
            u.setCognome("Root");
            u.setRuolo(Ruolo.ADMIN);
            u.setAttivo(true);
            return utenteRepository.save(u);
        });

        // Crea un TECNICO
        Utente tecnico = utenteRepository.findByEmail("tecnico@domoticlaboratories.com").orElseGet(() -> {
            Utente u = new Utente();
            u.setEmail("tecnico@domoticlaboratories.com");
            u.setPassword(passwordEncoder.encode("tecnico"));
            u.setNome("Mario");
            u.setCognome("Rossi");
            u.setRuolo(Ruolo.TECNICO);
            u.setAttivo(true);
            return utenteRepository.save(u);
        });

        // Crea un CLIENTE
        Utente cliente = utenteRepository.findByEmail("cliente@esempio.com").orElseGet(() -> {
            Utente u = new Utente();
            u.setEmail("cliente@esempio.com");
            u.setPassword(passwordEncoder.encode("cliente"));
            u.setNome("Luigi");
            u.setCognome("Verdi");
            u.setRuolo(Ruolo.CLIENTE);
            u.setAttivo(true);
            return utenteRepository.save(u);
        });

        // Resetta prodotti per il seeding
        prodottoRepository.deleteAll();
        categoriaRepository.deleteAll();

        // Categorie
        Categoria catHub = new Categoria(null, "Hub", "Hub e centralini domestici");
        catHub = categoriaRepository.save(catHub);

        Categoria catSicurezza = new Categoria(null, "Sicurezza", "Allarmi e telecamere");
        catSicurezza = categoriaRepository.save(catSicurezza);

        // 2. Prodotti Reali
        Prodotto p1 = new Prodotto("SH-HUB-01", "Home Hub Zigbee/Wi-Fi", new BigDecimal("129.99"), 50, true);
        p1.setDescrizione("L'hub centrale per il tuo ecosistema smart, compatibile con Zigbee 3.0 e Wi-Fi 6.");
        p1.setCategoria(catHub);
        prodottoRepository.save(p1);

        Prodotto p2 = new Prodotto("SC-CAM-PR", "Telecamera IP 360", new BigDecimal("199.50"), 22, true);
        p2.setDescrizione("Controllo totale degli ambienti con visione notturna AI-powered 360 gradi.");
        p2.setCategoria(catSicurezza);
        prodottoRepository.save(p2);

        Prodotto p3 = new Prodotto("ST-THM-01", "Termostato Smart Modulare", new BigDecimal("149.00"), 20, true);
        p3.setDescrizione("Massimo comfort termico con IA comportamentale. Impara le tue abitudini.");
        p3.setCategoria(catHub);
        prodottoRepository.save(p3);

        // 3. Assistenza e Lavori per la Dashboard
        // Cancello vecchi lavori per non duplicare al riavvio
        installazioneRepository.deleteAll();
        richiestaServizioRepository.deleteAll();

        RichiestaServizio richiesta = new RichiestaServizio();
        richiesta.setUtente(cliente);
        richiesta.setTipoProgetto(TipoProgetto.DOMOTICA);
        richiesta.setDescrizione("Installazione sistema di sicurezza 360 al piano terra.");
        richiesta.setStato(StatoServizio.IN_VALUTAZIONE);
        richiesta = richiestaServizioRepository.save(richiesta);

        Installazione installazione = new Installazione();
        installazione.setRichiestaServizio(richiesta);
        installazione.setTecnico(tecnico);
        installazione.setStato(StatoInstallazione.PROGRAMMATA);
        installazione.setDataPianificata(LocalDateTime.now().plusDays(2));
        installazioneRepository.save(installazione);
    }
}
