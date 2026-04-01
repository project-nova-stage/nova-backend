package com.nova.backend.service.assistenza;

import com.nova.backend.dto.assistenza.richiesta.RichiestaServizioRequest;
import com.nova.backend.dto.assistenza.risposta.RichiestaServizioResponse;
import com.nova.backend.model.assistenza.StatoServizio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestService {

    private final RichiestaServizioService richiestaServizioService;

    public ServiceRequestService(RichiestaServizioService richiestaServizioService) {
        this.richiestaServizioService = richiestaServizioService;
    }

    public RichiestaServizioResponse createRequest(RichiestaServizioRequest request) {
        return richiestaServizioService.creaRichiesta(request);
    }

    public List<RichiestaServizioResponse> getAllRequests() {
        return richiestaServizioService.ottieniTutte();
    }

    public Optional<RichiestaServizioResponse> getRequestById(Long id) {
        return richiestaServizioService.ottieniPerId(id);
    }

    public List<RichiestaServizioResponse> getRequestsByUser(Long utenteId) {
        return richiestaServizioService.ottieniPerUtente(utenteId);
    }

    public RichiestaServizioResponse updateStatus(Long id, StatoServizio nuovoStato) {
        return richiestaServizioService.aggiornaStato(id, nuovoStato);
    }

    public void deleteRequest(Long id) {
    
        throw new UnsupportedOperationException("eliminazione richieste non implementata");
    }
}
