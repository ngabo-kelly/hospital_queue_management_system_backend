package hospital.hospital_queue_management_system.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "queue_ticket")
public class QueueTicket {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private LocalDate ticketDate;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private int ticketNumber;

    @ManyToOne 
    @JoinColumn(name = "outpatient_id")
    private Outpatient outpatient;

    @ManyToOne 
    @JoinColumn( name = "nurse_id")
    private Nurse nurse;

    public QueueTicket(){

    }
    public UUID getId(){
        return id;
    }
    public LocalDate getTicketDate(){
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate){
        this.ticketDate = ticketDate;
    }

    public TicketStatus getStatus(){
        return status;
    }
    public void setStatus( TicketStatus status){
        this.status = status;

    }
    public int getTicketNumber(){
        return ticketNumber;
    }
    public void setTicketNumber(int ticketNumber){
        this.ticketNumber = ticketNumber;
    }
    public Outpatient getOutpatient(){
        return outpatient;
    }
    public void setOutpatient(Outpatient outpatient){
        this.outpatient = outpatient;
    }
    public Nurse getNurse(){
        return nurse;
    }
    public void setNurse(Nurse nurse){
        this.nurse = nurse;
    }


}
