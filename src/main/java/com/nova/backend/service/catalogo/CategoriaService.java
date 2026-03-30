package com.nova.backend.service.catalogo;

import com.nova.backend.dto.catalogo.CategoriaRequest;
import com.nova.backend.dto.catalogo.CategoriaResponse;
import com.nova.backend.mapper.catalogo.CategoriaMapper;
import com.nova.backend.model.catalogo.Categoria;
import com.nova.backend.repository.catalogo.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository repository, CategoriaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CategoriaResponse creaCategoria(CategoriaRequest request) {
        Categoria categoria = mapper.toEntity(request);
        return mapper.toResponse(repository.save(categoria));
    }

    public List<CategoriaResponse> ottieniTutte() {
        return repository.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }

    public List<CategoriaResponse> ottieniCategoriePrincipali() {
        return repository.findByCategoriaPadreIsNull().stream()
                .map(mapper::toResponse).collect(Collectors.toList());
    }
}