package com.nova.backend.repository.catalogo;

import com.nova.backend.model.catalogo.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository per la gestione del catalogo prodotti.
 * Sincronizzato con gli standard di Nova.
 */
@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    /** Restituisce tutti i prodotti attivi. */
    List<Prodotto> findByAttivoTrue();

    /** Filtra i prodotti per categoria. */
    List<Prodotto> findByCategoriaId(Long categoriaId);

    /** Cerca un prodotto tramite il suo codice univoco (SKU). */
    Optional<Prodotto> findBySku(String sku);

    /** Verifica esistenza per SKU, usato per prevenire duplicati. */
    boolean existsBySku(String sku);
}
