package com.nova.backend.controller.iot;

import com.nova.backend.dto.iot.risposta.DeviceLogDTO;
import com.nova.backend.dto.iot.risposta.DeviceResponseDTO;
import com.nova.backend.dto.iot.risposta.EnergyStatDTO;
import com.nova.backend.service.iot.DispositivoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller API per dispositivi IoT e statistiche energia.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/api/v1/dispositivi", "/api/v1/devices"})
public class DispositivoController {

    private final DispositivoService dispositivoService;

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping("/utente/{utenteId}")
    public ResponseEntity<List<DeviceResponseDTO>> ottieniDispositiviUtente(@PathVariable Long utenteId) {
        return ResponseEntity.ok(dispositivoService.ottieniPerUtente(utenteId));
    }

    @GetMapping("/{dispositivoId}/logs")
    public ResponseEntity<List<DeviceLogDTO>> ottieniLog(@PathVariable Long dispositivoId) {
        return ResponseEntity.ok(dispositivoService.ottieniLogDispositivo(dispositivoId));
    }

    @GetMapping("/{dispositivoId}/energia")
    public ResponseEntity<List<EnergyStatDTO>> ottieniStatisticheEnergia(
            @PathVariable Long dispositivoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInizio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFine) {
        return ResponseEntity.ok(dispositivoService.ottieniStatisticheEnergia(dispositivoId, dataInizio, dataFine));
    }
}
