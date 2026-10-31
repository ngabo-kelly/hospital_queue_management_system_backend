package hospital.hospital_queue_management_system.Controller;

import hospital.hospital_queue_management_system.Entity.Nurse;
import hospital.hospital_queue_management_system.Service.NurseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping("/api/nurses")
public class NurseController {

    @Autowired 
    private NurseService nurseService;

    @PostMapping("/save")
    public ResponseEntity<String> save(
        @RequestBody Nurse nurse) {
            return ResponseEntity.status(HttpStatus.CREATED)
            .body(nurseService.saveNurse(nurse));
        }

        @GetMapping("/all")
        public ResponseEntity<List<Nurse>>getAll() {
            return ResponseEntity.ok(nurseService.getAllNurses());
        }
        
        @GetMapping("/by-service")
        public List<Nurse>getNursesByService(
            @RequestParam String name) {
                return nurseService.getNursesByServiceName(name);
            }
        

        @GetMapping("/{id}")
        public ResponseEntity<?>getById(@PathVariable UUID id) {
            Optional<Nurse> result = nurseService.getNurseById(id);

            if(result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The nurse with that id does not exist");
            }

            return ResponseEntity.ok(result.get());
        }
        @PutMapping("/update/{id}")
        public ResponseEntity<String>update(
            @PathVariable UUID id,
            @RequestBody Nurse nurse) {

                String message = nurseService.updateNurse(id,nurse);

                if(message.contains("does not exist")){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message);
                }
                return ResponseEntity.ok(message);
            }
            @DeleteMapping("/delete/{id}")
            public ResponseEntity<String>delete(@PathVariable UUID id){
                String message = nurseService.deleteNurse(id);

                if (message.contains("does not exist")){
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message);
                }
                return ResponseEntity.ok(message);
            }
        }
        

    


