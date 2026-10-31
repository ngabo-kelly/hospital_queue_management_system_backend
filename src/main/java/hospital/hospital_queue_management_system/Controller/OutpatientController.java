package hospital.hospital_queue_management_system.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hospital.hospital_queue_management_system.Entity.Outpatient;
import hospital.hospital_queue_management_system.Service.OutpatientService;

@RestController
@RequestMapping("/api/outpatients")
public class OutpatientController {

    @Autowired
    private OutpatientService outpatientService;

    @PostMapping("/save")
    public ResponseEntity<String> saveOutpatient(
            @RequestBody Outpatient outpatient) {

        String message = outpatientService.saveOutpatient(outpatient);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Outpatient> getAllOutpatients() {
        return outpatientService.getAllOutpatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOutpatientById(@PathVariable UUID id) {
        Optional<Outpatient> outpatient = outpatientService.getOutpatientById(id);

        if (outpatient.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The Outpatient with that id does not exist");
        }
        return ResponseEntity.ok(outpatient.get());
    }

    @GetMapping("/by-last-name")
    public List<Outpatient>getOutpatientsByLastName(
        @RequestParam String lastName){
            return outpatientService.getOutpatientByLastName(lastName);
        }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateOutpatient(
            @PathVariable UUID id,
            @RequestBody Outpatient updated) {

        String message = outpatientService.updateOutpatient(id, updated);

        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOutpatient(@PathVariable UUID id) {
        String message = outpatientService.deleteOutpatient(id);

        if (message.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.ok(message);
    }
}
