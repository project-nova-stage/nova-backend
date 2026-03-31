package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.RichiestaServizio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaServizioRepository extends JpaRepository<RichiestaServizio, Long> {
    // Trova tutte le richieste fatte da un utente specifico
    List<RichiestaServizio> findByUtenteId(Long utenteId);
}