package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.MessaggioSupporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessaggioSupportoRepository extends JpaRepository<MessaggioSupporto, Long> {

    // Genera automaticamente: SELECT * FROM support_messages WHERE ticket_id = ?
    List<MessaggioSupporto> findByTicketId(Long ticketId);
}