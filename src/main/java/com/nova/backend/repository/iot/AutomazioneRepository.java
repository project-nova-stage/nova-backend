package com.nova.backend.repository.iot;

import com.nova.backend.model.iot.Automazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione delle automazioni IoT.
 */
@Repository
public interface AutomazioneRepository extends JpaRepository<Automazione, Long> {

    /** Restituisce tutte le automazioni associate a un dispositivo specifico. */
    List<Automazione> findByDispositivoId(Long dispositivoId);
}
