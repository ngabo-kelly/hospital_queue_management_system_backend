package hospital.hospital_queue_management_system.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import hospital.hospital_queue_management_system.Entity.Counter;
import hospital.hospital_queue_management_system.Repository.CounterRepository;

@Service
public class CounterService {

    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }
    public Counter saveCounter(Counter counter) {
        return counterRepository.save(counter);
    }
    public List<Counter>getAllCounters(){
        return counterRepository.findAll();
    }
    public Optional<Counter>getCounterById(UUID id) {
        return counterRepository.findById(id);
    }
    public Optional<Counter> updateCounter(UUID id , Counter updatedCounter){
        return counterRepository.findById(id).map(counter -> {
            counter.setCounterNumber(updatedCounter.getCounterNumber());
            counter.setName(updatedCounter.getName());
            return counterRepository.save(counter);
        });
    
    }

    public boolean deleteCounter(UUID id){
        if (!counterRepository.existsById(id)){
            return false;
        }
        counterRepository.deleteById(id);
        return true;
    }
}
