package com.nova.backend.repository.catalogo;

import com.nova.backend.model.catalogo.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository per la gestione delle categorie prodotto.
 * Sincronizzato con gli standard di Nova.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /** Recupera le categorie radice (senza categoria padre). */
    List<Categoria> findByCategoriaPadreIsNull();

    /** Recupera le sottocategorie dirette di una categoria padre. */
    List<Categoria> findByCategoriaPadreId(Long categoriaPadreId);

    /** Recupera una categoria tramite il suo slug univoco. */
    Optional<Categoria> findBySlug(String slug);
}
