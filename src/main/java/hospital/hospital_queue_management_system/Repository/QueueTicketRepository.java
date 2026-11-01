package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Entity.TicketStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

public interface QueueTicketRepository
        extends JpaRepository<QueueTicket, UUID> {

    @Modifying
    @Transactional
    @Query("""
            UPDATE QueueTicket q
            SET q.status = :skipped
            WHERE q.nurse.id = :nurseId
            AND q.ticketDate = :date
            AND q.status<> :served
            """)

    int cancelTicketsForNurse(
            @Param("nurseId") UUID nurseId,
            @Param("date") LocalDate date,
            @Param("skipped") TicketStatus skipped,
            @Param("served") TicketStatus served);

    @Query("""
            SELECT q.status,COUNT(q)
            FROM QueueTicket q
            GROUP BY q.status
            """)
    List<Object[]> countTicketsByStatus();

    Optional<QueueTicket> findById(UUID id);

    boolean existsByNurse_IdAndTicketDateAndStatusNot(
            UUID nurseId,
            LocalDate ticketDate,
            TicketStatus status

    );

    List<QueueTicket> findByStatusOrderByTicketDateAsc(
            TicketStatus status);

    List<QueueTicket> findByTicketDateBetweenOrderByTicketDateAsc(
            LocalDate start,
            LocalDate end);

}
