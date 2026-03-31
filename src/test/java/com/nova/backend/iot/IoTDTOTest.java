package com.nova.backend.iot;

import com.nova.backend.dto.iot.risposta.DeviceResponseDTO;
import com.nova.backend.mapper.iot.IoTMapper;
import com.nova.backend.model.iot.Dispositivo;
import com.nova.backend.model.iot.StatoDispositivo;
import com.nova.backend.model.catalogo.Prodotto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test di validazione per i DTO e Mapper del modulo IoT (in Main).
 */
class IoTDTOTest {

    @Test
    void testDeviceMapping() {
        Prodotto p = new Prodotto();
        p.setNome("Presa Smart Wi-Fi");

        Dispositivo d = new Dispositivo();
        d.setCodiceDispositivo("DEV-99");
        d.setIndirizzoMac("AA:BB:CC:DD:EE:FF");
        d.setStato(StatoDispositivo.ONLINE);
        d.setPosizione("Cucina");
        d.setProdotto(p);

        DeviceResponseDTO response = IoTMapper.toDeviceResponse(d);

        assertEquals("DEV-99", response.getDeviceCode());
        assertEquals("ONLINE", response.getStatus());
        assertEquals("Presa Smart Wi-Fi", response.getModelName());
    }
}
