package hospital.hospital_queue_management_system.Service;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Entity.TicketStatus;
import hospital.hospital_queue_management_system.Repository.QueueTicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QueueTicketService {

    @Autowired
    private QueueTicketRepository ticketRepo;

    public List<QueueTicket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public String updateTicketStatus(UUID id, TicketStatus newStatus) {
        Optional<QueueTicket> optionalTicket = ticketRepo.findById(id);

        if (optionalTicket.isEmpty()) {
            return "The Queue ticket with that id does not exist";
        }

        QueueTicket ticket = optionalTicket.get();
        TicketStatus currentStatus = ticket.getStatus();

        boolean validChange = (currentStatus == TicketStatus.WAITING &&
                (newStatus == TicketStatus.CALLED ||
                        newStatus == TicketStatus.SKIPPED))
                ||
                (currentStatus == TicketStatus.CALLED &&
                        (newStatus == TicketStatus.SERVED ||
                                newStatus == TicketStatus.SKIPPED));

        if (!validChange) {
            return "Cannot change status from " +
                    currentStatus + "to" + newStatus;
        }
        ticket.setStatus(newStatus);
        ticketRepo.save(ticket);

        return "Queue ticket status updated successfully";
    }

    public String saveTicket(QueueTicket ticket) {
        if (ticket.getTicketDate().isBefore(LocalDate.now())) {
            return "Queue ticket date cannot be in the past";
        }

        boolean alreadyHasTicket = ticketRepo.existsByNurse_IdAndTicketDateAndStatusNot(
                ticket.getNurse().getId(),
                ticket.getTicketDate(),
                TicketStatus.SKIPPED

        );
        if (alreadyHasTicket) {
            return "Nurse already has an active ticket on that date";
        }

        ticket.setStatus(TicketStatus.WAITING);
        ticketRepo.save(ticket);
        return "Queue ticket saved successfully";
    }

    public Optional<QueueTicket> getTicketById(UUID id) {
        return ticketRepo.findById(id);

    }

    public List<QueueTicket> getTicketByStatus(TicketStatus status) {
        return ticketRepo.findByStatusOrderByTicketDateAsc(status);
    }

    public List<QueueTicket> getTicketsBetweenDates(
            LocalDate start,
            LocalDate end) {
        return ticketRepo
                .findByTicketDateBetweenOrderByTicketDateAsc(start, end);
    }

    public String deleteTicket(UUID id) {

        return "Implement the deletion rules";
    }
}
