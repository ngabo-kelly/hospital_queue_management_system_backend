package hospital.hospital_queue_management_system.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hospital.hospital_queue_management_system.Entity.Counter;
import hospital.hospital_queue_management_system.Service.CounterService;

@RestController 
@RequestMapping("/api/counters")
public class CounterController {

    private final CounterService counterService;

public CounterController(CounterService counterService){
    this.counterService = counterService;
}

@PostMapping("/save")
public ResponseEntity<?>saveCounter(@RequestBody Counter counter){
    Counter savedCounter = counterService.saveCounter(counter);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCounter);
} 

@GetMapping("/all")
public ResponseEntity<List<Counter>>getAllCounters(){
    return ResponseEntity.ok(counterService.getAllCounters());
}
@GetMapping("/{id}")
public ResponseEntity<?> getCounterById(@PathVariable UUID id ){
    return counterService.getCounterById(id)
    .<ResponseEntity<?>>map(ResponseEntity::ok)
    .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND)
    .body("The counter with that id does not exist"));

}
@PutMapping("/update/{id}")
public ResponseEntity<?>updateCounter(
    @PathVariable UUID id,
    @RequestBody Counter counter) {

        return counterService.updateCounter(id, counter)
        .<ResponseEntity<?>>map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The counter with that id does not exist"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteCounter(@PathVariable UUID id) {
        if (counterService.deleteCounter(id)){
            return ResponseEntity.ok("Counter deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("The counter with that id does not exist");
    }



}
