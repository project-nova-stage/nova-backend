package com.nova.backend.service.catalogo;

import com.nova.backend.dto.catalogo.CategoriaDTO;
import com.nova.backend.mapper.catalogo.CategoriaMapper;
import com.nova.backend.model.catalogo.Categoria;
import com.nova.backend.repository.catalogo.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servizio per la gestione delle categorie dei prodotti.
 */
@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Crea una nuova categoria.
     */
    public CategoriaDTO creaCategoria(CategoriaDTO dto) {
        Categoria categoria = mapper.toEntity(dto);
        Categoria salvata = repository.save(categoria);
        return mapper.toResponse(salvata);
    }

    /**
     * Ottiene tutte le categorie.
     */
    public List<CategoriaDTO> ottieniTutte() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Ottiene una categoria per ID.
     */
    public CategoriaDTO ottieniPerId(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Categoria non trovata con ID: " + id, org.springframework.http.HttpStatus.NOT_FOUND));
    }

    /**
     * Aggiorna una categoria esistente.
     */
    public CategoriaDTO aggiornaCategoria(Long id, CategoriaDTO dto) {
        Categoria esistente = repository.findById(id)
                .orElseThrow(() -> new com.nova.backend.exception.EccezioneApplicativa("Categoria non trovata con ID: " + id, org.springframework.http.HttpStatus.NOT_FOUND));

        esistente.setNome(dto.getNome());

        Categoria aggiornata = repository.save(esistente);
        return mapper.toResponse(aggiornata);
    }

    /**
     * Elimina una categoria.
     */
    public void eliminaCategoria(Long id) {
        if (!repository.existsById(id)) {
            throw new com.nova.backend.exception.EccezioneApplicativa("Categoria non trovata con ID: " + id, org.springframework.http.HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}


