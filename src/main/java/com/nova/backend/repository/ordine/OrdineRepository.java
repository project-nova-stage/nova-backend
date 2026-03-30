package com.nova.backend.repository.ordine;

import com.nova.backend.model.ordine.Ordine;
import com.nova.backend.model.ordine.StatoOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    Optional<Ordine> findByNumeroOrdine(String numeroOrdine);

    List<Ordine> findByUtenteId(Long utenteId);

    List<Ordine> findByStato(StatoOrdine stato);

}
