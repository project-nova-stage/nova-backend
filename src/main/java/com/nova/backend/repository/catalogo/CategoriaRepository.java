package com.nova.backend.repository.catalogo;

import com.nova.backend.model.catalogo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Utile per recuperare solo le categorie di primo livello (quelle senza padre)
    List<Categoria> findByCategoriaPadreIsNull();

    // Utile per la ricerca tramite URL (slug)
    Categoria findBySlug(String slug);
}