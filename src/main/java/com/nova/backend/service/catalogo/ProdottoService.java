package com.nova.backend.service.catalogo;

import com.nova.backend.dto.catalogo.ProdottoRequest;
import com.nova.backend.dto.catalogo.ProdottoResponse;
import com.nova.backend.mapper.catalogo.ProdottoMapper;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.repository.catalogo.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdottoService {

    private final ProdottoRepository repository;
    private final ProdottoMapper mapper;

    public ProdottoService(ProdottoRepository repository, ProdottoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Grazie al CascadeType.ALL e al Mapper, questo singolo save()
    // scriverà automaticamente anche nelle tabelle product_images e product_specifications!
    public ProdottoResponse creaProdotto(ProdottoRequest request) {
        Prodotto prodotto = mapper.toEntity(request);
        Prodotto salvato = repository.save(prodotto);
        return mapper.toResponse(salvato);
    }

    public List<ProdottoResponse> ottieniTutti() {
        return repository.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public List<ProdottoResponse> ottieniAttivi() {
        return repository.findByAttivoTrue().stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public Optional<ProdottoResponse> ottieniPerSku(String sku) {
        return repository.findBySku(sku).map(mapper::toResponse);
    }
}