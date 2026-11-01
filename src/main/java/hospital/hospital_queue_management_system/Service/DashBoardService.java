package hospital.hospital_queue_management_system.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.hospital_queue_management_system.Repository.QueueTicketRepository;
import hospital.hospital_queue_management_system.Repository.NurseRepository;
import hospital.hospital_queue_management_system.Repository.OutpatientRepository;
import hospital.hospital_queue_management_system.Repository.CounterRepository;

@Service
public class DashBoardService {

    @Autowired 
    private QueueTicketRepository ticketRepo;

    @Autowired 
    private NurseRepository nurseRepo;

    @Autowired 
    private OutpatientRepository outpatientRepo;

    @Autowired 
    private CounterRepository counterRepo;

    public  Map<String,Long>getDashboard(){
        Map<String,Long>dashboard = new LinkedHashMap<>();
        dashboard.put("totalQueueTickets",ticketRepo.count());

        dashboard.put("totalNurses",nurseRepo.count());

        dashboard.put("totalOutpatients",outpatientRepo.count());

        dashboard.put("totalCounters",counterRepo.count());

        dashboard.put(
            "FreeCounters",
            (long) counterRepo.findFreeCounter().size()
        );
        return dashboard;
            
    }
}
