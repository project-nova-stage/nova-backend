package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.Installazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallazioneRepository extends JpaRepository<Installazione, Long> {
    // Utile per il frontend: "dammi tutte le installazioni assegnate a un certo tecnico"
    List<Installazione> findByTecnicoId(Long tecnicoId);
}