package com.nova.backend.service.catalogo;

import com.nova.backend.dto.catalogo.richiesta.ProdottoRequestDTO;
import com.nova.backend.dto.catalogo.risposta.ProdottoResponseDTO;
import com.nova.backend.mapper.catalogo.ProdottoMapper;
import com.nova.backend.model.catalogo.Categoria;
import com.nova.backend.model.catalogo.Prodotto;
import com.nova.backend.repository.catalogo.CategoriaRepository;
import com.nova.backend.repository.catalogo.ProdottoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servizio per la gestione del catalogo prodotti.
 */
@Service
@Transactional
public class ProdottoService {

    private final ProdottoRepository repository;
    private final ProdottoMapper mapper;
    private final CategoriaRepository categoriaRepository;

    public ProdottoService(ProdottoRepository repository, 
                          ProdottoMapper mapper,
                          CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Crea un nuovo prodotto nel catalogo.
     */
    public ProdottoResponseDTO creaProdotto(ProdottoRequestDTO request) {
        if (repository.existsBySku(request.getSku())) {
            throw new RuntimeException("Esiste già un prodotto con questo SKU: " + request.getSku());
        }

        Prodotto prodotto = mapper.toEntity(request);
        
        if (request.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria non trovata: " + request.getCategoriaId()));
            prodotto.setCategoria(cat);
        }

        Prodotto salvato = repository.save(prodotto);
        return mapper.toResponse(salvato);
    }

    /**
     * Ottiene tutti i prodotti attivi.
     */
    public List<ProdottoResponseDTO> ottieniProdottiAttivi() {
        return repository.findByAttivoTrue().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Ottiene un prodotto tramite SKU.
     */
    public ProdottoResponseDTO ottieniPerSku(String sku) {
        return repository.findBySku(sku)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con SKU: " + sku));
    }

    /**
     * Ottiene un prodotto tramite ID.
     */
    public ProdottoResponseDTO ottieniPerId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con ID: " + id));
    }

    /**
     * Aggiorna un prodotto esistente.
     */
    public ProdottoResponseDTO aggiornaProdotto(Long id, ProdottoRequestDTO request) {
        Prodotto esistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con ID: " + id));

        esistente.setNome(request.getNome());
        esistente.setPrezzo(request.getPrezzo());
        esistente.setQuantitaDisponibile(request.getQuantitaDisponibile());
        
        if (request.getAttivo() != null) {
            esistente.setAttivo(request.getAttivo());
        }
        
        if (request.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria non trovata: " + request.getCategoriaId()));
            esistente.setCategoria(cat);
        }

        Prodotto aggiornato = repository.save(esistente);
        return mapper.toResponse(aggiornato);
    }

    /**
     * Elimina logicamente o fisicamente un prodotto.
     */
    public void eliminaProdotto(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Prodotto non trovato con ID: " + id);
        }
        repository.deleteById(id);
    }
}