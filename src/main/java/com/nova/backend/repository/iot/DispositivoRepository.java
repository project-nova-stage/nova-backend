package com.nova.backend.repository.iot;

import com.nova.backend.model.iot.Dispositivo;
import com.nova.backend.model.iot.StatoDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione dei dispositivi IoT registrati dagli utenti.
 */
@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    /** Restituisce tutti i dispositivi di un utente. */
    List<Dispositivo> findByUtenteId(Long utenteId);

    /** Filtra i dispositivi per stato operativo (es. ONLINE, OFFLINE). */
    List<Dispositivo> findByStato(StatoDispositivo stato);

    /** Verifica se un codice dispositivo è già registrato nel sistema. */
    boolean existsByCodiceDispositivo(String codiceDispositivo);
}
