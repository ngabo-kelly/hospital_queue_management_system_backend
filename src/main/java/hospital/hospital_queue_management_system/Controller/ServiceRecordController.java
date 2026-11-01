package hospital.hospital_queue_management_system.Controller;

import hospital.hospital_queue_management_system.Entity.ServiceRecord;
import hospital.hospital_queue_management_system.Service.ServiceRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController 
@RequestMapping("/api/service-records")
public class ServiceRecordController {

    @Autowired 
    private ServiceRecordService recordService;

    @PostMapping("/save")
    public ResponseEntity<String>saveServiceRecord(
        @RequestParam UUID queueTicketId,
        @RequestBody ServiceRecord record){

            String message = 
            recordService.saveServiceRecord(record,queueTicketId);

            if(message.equals("The queue ticket with that id does not exist")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);

            }else if (message.equals("Service records are only for served queue tickets")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            } else {
                return ResponseEntity.status(HttpStatus.CREATED).body(message);
            }
        }
    @GetMapping("/outpatient/{fileNumber}")
    public List<ServiceRecord>getHistory(
        @PathVariable String fileNumber){
            return recordService.getHistoryByOutpatient(fileNumber);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String>deleteServiceRecord(
            @PathVariable UUID id) {

                String message = recordService.deleteServiceRecord(id);

                if(message.equals("The service record with that id does not exist")){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                } else {
                    return ResponseEntity.ok(message);
                }
            }
        


}
