package com.nova.backend.repository.assistenza;

import com.nova.backend.model.assistenza.TicketSupporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketSupportoRepository extends JpaRepository<TicketSupporto, Long> {
    // Spring Data JPA fornisce automaticamente save(), findAll(), findById(), deleteById()
}