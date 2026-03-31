package com.nova.backend.mapper.iot;

import com.nova.backend.dto.iot.risposta.DeviceLogDTO;
import com.nova.backend.dto.iot.risposta.DeviceResponseDTO;
import com.nova.backend.dto.iot.risposta.EnergyStatDTO;
import com.nova.backend.model.iot.Dispositivo;
import com.nova.backend.model.iot.LogDispositivo;
import com.nova.backend.model.iot.StatisticaEnergia;

/**
 * Mapper ufficiale del modulo IoT.
 */
public class IoTMapper {

    public static DeviceResponseDTO toDeviceResponse(Dispositivo dispositivo) {
        if (dispositivo == null) return null;

        DeviceResponseDTO dto = new DeviceResponseDTO();
        dto.setId(dispositivo.getId());
        dto.setDeviceCode(dispositivo.getCodiceDispositivo());
        dto.setMacAddress(dispositivo.getIndirizzoMac());
        dto.setStatus(dispositivo.getStato() != null ? dispositivo.getStato().name() : null);
        dto.setLocation(dispositivo.getPosizione()); 
        
        if (dispositivo.getProdotto() != null) {
            dto.setModelName(dispositivo.getProdotto().getNome());
        }

        return dto;
    }

    public static DeviceLogDTO toLogDTO(LogDispositivo log) {
        if (log == null) return null;

        DeviceLogDTO dto = new DeviceLogDTO();
        dto.setEventType(log.getTipoEvento() != null ? log.getTipoEvento().name() : null);
        dto.setMessage(log.getMessaggio());
        dto.setCreatedAt(log.getCreatedAt());
        return dto;
    }

    public static EnergyStatDTO toEnergyStatDTO(StatisticaEnergia stat) {
        if (stat == null) return null;

        EnergyStatDTO dto = new EnergyStatDTO();
        dto.setDate(stat.getData());
        dto.setConsumptionKwh(stat.getConsumoKwh());
        return dto;
    }
}
