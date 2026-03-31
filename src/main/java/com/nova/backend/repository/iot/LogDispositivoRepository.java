package com.nova.backend.repository.iot;

import com.nova.backend.model.iot.LogDispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione dei log degli eventi dei dispositivi IoT.
 */
@Repository
public interface LogDispositivoRepository extends JpaRepository<LogDispositivo, Long> {

    /** Recupera tutti i log di un dispositivo, ordinati dal più recente. */
    List<LogDispositivo> findByDispositivoIdOrderByCreatedAtDesc(Long dispositivoId);
}
