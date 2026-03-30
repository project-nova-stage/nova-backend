package com.nova.backend.repository.catalogo;

import com.nova.backend.model.catalogo.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    // Cerca un prodotto tramite il suo codice univoco
    Optional<Prodotto> findBySku(String sku);

    // Trova tutti i prodotti associati a una specifica categoria
    List<Prodotto> findByCategoriaId(Long categoriaId);

    // Recupera solo i prodotti attualmente in vendita
    List<Prodotto> findByAttivoTrue();
}