package hospital.hospital_queue_management_system.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hospital.hospital_queue_management_system.Entity.Service;
import hospital.hospital_queue_management_system.Service.ServiceService;

@RestController 
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired 
    private ServiceService serviceService;

    @PostMapping("/save")
    public ResponseEntity<String>saveService(
        @RequestBody Service service) {
            String message = serviceService.saveService(service);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }

        @GetMapping("/all")
        public List<Service>getAllServices(){
            return serviceService.getAllServices();
        }

        @GetMapping("/{id}")
        public ResponseEntity<?>getServiceById(@PathVariable UUID id){
            Optional<Service> service = serviceService.getServiceById(id);

            if(service.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The service with that id does not exist");
            }
            return ResponseEntity.ok(service.get());
        }

        @GetMapping("/unused")
        public List<Service>getUnsusedServices(){
            return serviceService.getUnusedServices();
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<String> updateService(
            @PathVariable UUID id,
            @RequestBody Service updated) {

                String message = serviceService.updateService(id,updated);

                if(message.contains("does not exist")){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                }
                return ResponseEntity.ok(message);
            }

            @DeleteMapping("/delete/{id}")
            public ResponseEntity<String>deleteService(@PathVariable UUID id){
                String message = serviceService.deleteService(id);

                if (message.contains("does not exist")){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
                }
                return ResponseEntity.ok(message);
        }
    }
    


