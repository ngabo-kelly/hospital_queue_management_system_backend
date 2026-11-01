package hospital.hospital_queue_management_system.Service;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Entity.TicketStatus;
import hospital.hospital_queue_management_system.Entity.Nurse;
import hospital.hospital_queue_management_system.Entity.Outpatient;

import hospital.hospital_queue_management_system.Repository.QueueTicketRepository;
import hospital.hospital_queue_management_system.Repository.ServiceRecordRepository;
import hospital.hospital_queue_management_system.Repository.NurseRepository;
import hospital.hospital_queue_management_system.Repository.OutpatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QueueTicketService {

    public Page<QueueTicket> getQueueTicketPage( int page,int size){
        Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by("ticketDate").ascending()
        );
        return ticketRepo.findAll(pageable);
    }

    public String cancelTicketsForNurse(UUID nurseId, LocalDate date){

        int numberOfTickets = ticketRepo.cancelTicketsForNurse(
            nurseId,
            date,
            TicketStatus.SKIPPED,
            TicketStatus.SERVED
        );

    return numberOfTickets + "Queue tickets Cancelled";
    }
    public List<Object[]>getTicketStatistics(){
        return ticketRepo.countTicketsByStatus();
    }
   
    @Autowired
    private QueueTicketRepository ticketRepo;

     @Autowired 
    private ServiceRecordRepository recordRepo;
@Autowired 
private NurseRepository nurseRepo;

@Autowired 
private OutpatientRepository outpatientRepo;

    public List<QueueTicket> getAllTickets() {
        return ticketRepo.findAll();
    }

    public String updateTicket(UUID id, QueueTicket updatedTicket) {

        Optional<QueueTicket> result = ticketRepo.findById(id);

        if (result.isEmpty()) {
            return "The Queue ticket with that id does not exist";
        }
        if(updatedTicket.getTicketDate() ==null){
            return "Ticket date is required";
        }
        if(updatedTicket.getNurse() == null ||
        updatedTicket.getNurse().getId() == null ||
        !nurseRepo.existsById(updatedTicket.getNurse().getId())){
            return "The nurse with that id does not exist";
        }
        if (updatedTicket.getOutpatient() == null ||
        updatedTicket.getOutpatient().getId() == null ||
        !outpatientRepo.existsById(updatedTicket.getOutpatient().getId())){
            return "The outpatient with that id does not exists";
        }

        QueueTicket existing  = result.get();
        

        boolean duplicate = 
        ticketRepo.existsByNurse_IdAndTicketDateAndStatusNotAndIdNot(
            updatedTicket.getNurse().getId(),
            updatedTicket.getTicketDate(),
            TicketStatus.SKIPPED,
            id
         );
       

        if (duplicate){
            return "Nurse already has ana active ticket on that date";

        }
        existing.setTicketDate(updatedTicket.getTicketDate());
        existing.setTicketNumber(updatedTicket.getTicketNumber());
        existing.setNurse(updatedTicket.getNurse());
        existing.setOutpatient(updatedTicket.getOutpatient());

        ticketRepo.save(existing);
        return "Queue ticket updated successfully";
    }

    public String saveTicket(QueueTicket ticket) {
        if (ticket.getTicketDate().isBefore(LocalDate.now())) {
            return "Queue ticket date cannot be in the past";
        }
        if(ticket.getTicketDate() == null) {
            return "Ticket date is Required";
        }
        if(ticket.getNurse() == null ||
        ticket.getNurse().getId() == null||
        !nurseRepo.existsById(ticket.getNurse().getId())){
            return "The nurse with that id does not exist";
        }


        if (ticket.getOutpatient() == null ||
        ticket.getOutpatient().getId() == null ||
        !outpatientRepo.existsById(ticket.getOutpatient().getId())){
            return "The Outpatient with that id does not exists";
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

        Optional<QueueTicket>result = ticketRepo.findById(id);

        if (result.isEmpty()){
            return "The queue ticket with that id does not exists";
        }
        QueueTicket ticket = result.get();
        if (recordRepo.existsByQueueTicket(ticket)){
            return "That queue ticket has service records,delete them first";
        }
        ticketRepo.delete(ticket);
        return "Queue ticket deleted successfully";
    }

    public String updateTicketStatus(UUID id,TicketStatus newStatus){

        Optional<QueueTicket>result = ticketRepo.findById(id);

        if (result.isEmpty()) {
            return "The queue ticket with that id doe not exist";
        }
        if (newStatus == null ) {
            return "New status is required";
        }
        QueueTicket ticket = result.get();
        TicketStatus currentStatus = ticket.getStatus();

        boolean validChange =
        (currentStatus == TicketStatus.WAITING &&
        (newStatus == TicketStatus.CALLED ||
        newStatus == TicketStatus.SKIPPED))
        ||
        currentStatus == TicketStatus.CALLED &&
        (newStatus ==TicketStatus.SERVED ||
        (newStatus == TicketStatus.SKIPPED));
        
        if (!validChange){
            return "Cannot change status from "+
            currentStatus + "to" + newStatus;
        }
        ticket.setStatus(newStatus);
        ticketRepo.save(ticket);
        return "Queue ticket ststus updated successfully";
        }
    }


       

