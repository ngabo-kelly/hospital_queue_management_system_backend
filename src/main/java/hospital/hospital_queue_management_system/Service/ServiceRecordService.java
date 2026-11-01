package hospital.hospital_queue_management_system.Service;

import hospital.hospital_queue_management_system.Entity.QueueTicket;
import hospital.hospital_queue_management_system.Entity.TicketStatus;
import hospital.hospital_queue_management_system.Entity.ServiceRecord;


import hospital.hospital_queue_management_system.Repository.QueueTicketRepository;
import hospital.hospital_queue_management_system.Repository.ServiceRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceRecordService {

    @Autowired 
    private ServiceRecordRepository recordRepo;
        
    @Autowired 
    private QueueTicketRepository ticketRepo;

    public String saveServiceRecord(ServiceRecord record,UUID queueTicketId){

        Optional<QueueTicket>optionalTicket = ticketRepo.findById(queueTicketId);
        if (optionalTicket.isEmpty()){
            return "The queue ticket with that id does not exist";
        }
        QueueTicket ticket = optionalTicket.get();
        
        if(ticket.getStatus() != TicketStatus.SERVED){
            return "Service records are only for SERVED queue tickets";
        }
        record.setRecordedDate(LocalDate.now());
        record.setQueueTicket(ticket);

        recordRepo.save(record);

        return "Service record saved successfully";
    }

    public List<ServiceRecord>getHistoryByOutpatient(String fileNumber){
        return recordRepo.findHistoryByFileNumber(fileNumber);
    }
    public String deleteServiceRecord(UUID id){
        Optional<ServiceRecord>optionalRecord =
        recordRepo.findById(id);

        if(optionalRecord.isEmpty()){
            return "The service record with that id does not exist";
        }
        recordRepo.delete(optionalRecord.get());

        return "Service record deleted successfully";
    }
    }

