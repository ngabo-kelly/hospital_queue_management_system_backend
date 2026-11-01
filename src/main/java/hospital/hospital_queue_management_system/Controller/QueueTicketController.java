package hospital.hospital_queue_management_system.Controller;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Service.QueueTicketService;
import hospital.hospital_queue_management_system.Entity.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/queue-tickets")
public class QueueTicketController {

    @Autowired
    private QueueTicketService ticketService;

    @PostMapping("/save")

    public ResponseEntity<String> save(
            @RequestBody QueueTicket ticket) {

        String message = ticketService.saveTicket(ticket);

        if (message.contains("past")) {
            return ResponseEntity.badRequest().body(message);
        }
        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        if (message.contains("active ticket")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/page")
    public Page<QueueTicket> getQueueTicketsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ticketDate,asc") String sort) {

        return ticketService.getQueueTicketPage(page, size );
    }

    @GetMapping("/all")
    public List<QueueTicket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/stats/by-status")
    public List<Object[]> getTicketStatistics() {
        return ticketService.getTicketStatistics();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<QueueTicket> result = ticketService.getTicketById(id);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The queue ticket with that id does not exist");
        }
        return ResponseEntity.ok(result.get());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateTicketStatus(
            @PathVariable UUID id,
            @RequestParam TicketStatus status) {

        String message = ticketService.updateTicketStatus(id, status);

        if (message.equals("The queue ticket with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else if (message.startsWith("Cannot change status")) {
            return ResponseEntity.badRequest().body(message);
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping("/between")
    public List<QueueTicket> getTicketsBetweenDates(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return ticketService.getTicketsBetweenDates(startDate, endDate);

    }

    @GetMapping("/by-status")
    public List<QueueTicket> getTicketByStatus(
            @RequestParam TicketStatus status) {

        return ticketService.getTicketByStatus(status);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(

            @PathVariable UUID id,
            @RequestBody QueueTicket ticket) {
       
                String message = ticketService.updateTicket(id,ticket);

                if(message.contains("Does not exist")){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                }
                if (message.equals("Nurse already has an active ticket on that date")){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
                }
                if (message.equals("Ticket date is required")){
                    return ResponseEntity.badRequest().body(message);
                }
                return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = ticketService.deleteTicket(id);

        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message);
        }
        if (message.contains("service records")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(message);
        }
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelTicketsForNurse(
            @RequestParam UUID nurseId,
            @RequestParam String date) {

        String message = ticketService.cancelTicketsForNurse(
                nurseId,
                LocalDate.parse(date));

        return ResponseEntity.ok(message);
    }

}
