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

    public List<QueueTicket>getAllTickets() {
        return ticketRepo.findAll();
    }

    public Optional<QueueTicket>getTicketById(UUID id) {
        return ticketRepo.findById(id);

    }
    public List<QueueTicket>getTicketByStatus(TicketStatus status){
        return ticketRepo.findByStatusOrderByTicketDateAsc(status);
    }
    public List<QueueTicket>getTicketsBetweenDates(
        LocalDate start,
        LocalDate end){
            return ticketRepo
            .findByTicketDateBetweenOrderByTicketDateAsc(start,end);
        }
    

    public String saveTicket(QueueTicket ticket){

        return "Implement the updated rules";
    }

    public String deleteTicket(UUID id){

        return "Implement the deletion rules";
    }
}
