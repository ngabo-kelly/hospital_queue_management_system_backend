package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Entity.TicketStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

public interface QueueTicketRepository
extends JpaRepository<QueueTicket,UUID>{

    Optional<QueueTicket>findById(UUID id);

    boolean existsByNurse_IdAndTicketDateAndStatusNot(
        UUID nurseId,
        LocalDate ticketDate,
        TicketStatus status
    
    );

    List<QueueTicket> findByStatusOrderByTicketDateAsc(
        TicketStatus status);
        List<QueueTicket>findByTicketDateBetweenOrderByTicketDateAsc(
            LocalDate start,
            LocalDate end);
        
    }


