package hospital.hospital_queue_management_system.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity 
@Table(name = "service_record")
public class ServiceRecord {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String serviceName;
    private String notes;
    private int durationMinutes;
    private LocalDate recordedDate;

    @ManyToOne 
    @JoinColumn(name = "queue_ticket_id")
    private QueueTicket queueTicket;

    public ServiceRecord(){

    }
    public UUID getId(){
        return id;
    }
    public void setId(UUID id){
        this.id = id;
    }
    public String getServiceName(){
        return serviceName;
    }
    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public int getDurationMinutes(){
        return durationMinutes;
    }
    public void setDurationMinutes(int durationMinutes){
        this.durationMinutes = durationMinutes;
    }
    public LocalDate getRecordedDate(){
        return recordedDate;
    }
    public void setRecordedDate(LocalDate recordedDate){
        this.recordedDate = recordedDate;
    }
    public QueueTicket getQueueTicket(){
        return queueTicket;
    }
    public void setQueueTicket(QueueTicket queueTicket){
        this.queueTicket = queueTicket;
    }

}
