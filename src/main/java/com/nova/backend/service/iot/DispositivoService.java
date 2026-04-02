package com.nova.backend.service.iot;

import com.nova.backend.dto.iot.risposta.DeviceLogDTO;
import com.nova.backend.dto.iot.risposta.DeviceResponseDTO;
import com.nova.backend.dto.iot.risposta.EnergyStatDTO;
import com.nova.backend.mapper.iot.IoTMapper;
import com.nova.backend.repository.iot.DispositivoRepository;
import com.nova.backend.repository.iot.LogDispositivoRepository;
import com.nova.backend.repository.iot.StatisticaEnergiaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * Servizio di lettura dati per dashboard dispositivi e consumi energia.
 */
@Service
public class DispositivoService {

    private final DispositivoRepository dispositivoRepository;
    private final LogDispositivoRepository logDispositivoRepository;
    private final StatisticaEnergiaRepository statisticaEnergiaRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository,
                              LogDispositivoRepository logDispositivoRepository,
                              StatisticaEnergiaRepository statisticaEnergiaRepository) {
        this.dispositivoRepository = dispositivoRepository;
        this.logDispositivoRepository = logDispositivoRepository;
        this.statisticaEnergiaRepository = statisticaEnergiaRepository;
    }

    public List<DeviceResponseDTO> ottieniPerUtente(Long utenteId) {
        return dispositivoRepository.findByUtenteId(utenteId).stream()
                .map(IoTMapper::toDeviceResponse)
                .toList();
    }

    public List<DeviceLogDTO> ottieniLogDispositivo(Long dispositivoId) {
        return logDispositivoRepository.findByDispositivoIdOrderByCreatedAtDesc(dispositivoId).stream()
                .map(IoTMapper::toLogDTO)
                .toList();
    }

    public List<EnergyStatDTO> ottieniStatisticheEnergia(Long dispositivoId, LocalDate dataInizio, LocalDate dataFine) {
        LocalDate fine = dataFine != null ? dataFine : LocalDate.now();
        LocalDate inizio = dataInizio != null ? dataInizio : fine.minusDays(30);

        return statisticaEnergiaRepository.findByDispositivoIdAndDataBetween(dispositivoId, inizio, fine).stream()
                .sorted(Comparator.comparing(stat -> stat.getData()))
                .map(IoTMapper::toEnergyStatDTO)
                .toList();
    }
}
