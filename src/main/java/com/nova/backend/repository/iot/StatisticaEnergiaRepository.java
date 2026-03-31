package com.nova.backend.repository.iot;

import com.nova.backend.model.iot.StatisticaEnergia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository per le statistiche di consumo energetico dei dispositivi IoT.
 */
@Repository
public interface StatisticaEnergiaRepository extends JpaRepository<StatisticaEnergia, Long> {

    /**
     * Recupera le statistiche di un dispositivo in un intervallo di date.
     * Usato per generare grafici e report di consumo.
     */
    List<StatisticaEnergia> findByDispositivoIdAndDataBetween(Long dispositivoId, LocalDate dataInizio, LocalDate dataFine);
}
