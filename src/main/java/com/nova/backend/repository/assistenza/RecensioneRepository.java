package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    // Per calcolare la media voti e mostrare i commenti nella pagina del prodotto
    List<Recensione> findByProdottoId(Long prodottoId);

    // Per la dashboard dell'utente (le sue recensioni)
    List<Recensione> findByUtenteId(Long utenteId);
}