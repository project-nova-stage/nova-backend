package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificaRepository extends JpaRepository<Notifica, Long> {
    // Trova tutte le notifiche di un utente, magari ordinandole dalla più recente in futuro
    List<Notifica> findByUtenteId(Long utenteId);

    // Trova solo quelle non ancora lette (per mostrare il classico "pallino rosso" sull'icona della campanella)
    List<Notifica> findByUtenteIdAndLettaFalse(Long utenteId);
}