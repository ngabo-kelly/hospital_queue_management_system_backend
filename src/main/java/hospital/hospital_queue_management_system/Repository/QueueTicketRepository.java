package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Entity.TicketStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import java.time.LocalDate;

public interface QueueTicketRepository
extends JpaRepository<QueueTicket,UUID>{

    List<QueueTicket> findByStatusOrderByTicketDateAsc(
        TicketStatus status);
        List<QueueTicket>findByTicketDateBetweenOrderByTicketDateAsc(
            LocalDate start,
            LocalDate end);
        
    }


