package hospital.hospital_queue_management_system.Service;

import hospital.hospital_queue_management_system.Entity.Nurse;
import hospital.hospital_queue_management_system.Entity.Counter;
import hospital.hospital_queue_management_system.Repository.ServiceRepository;
import hospital.hospital_queue_management_system.Repository.CounterRepository;
import hospital.hospital_queue_management_system.Repository.NurseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NurseService {

    @Autowired 
    private NurseRepository nurseRepo;

    @Autowired 
    private ServiceRepository serviceRepo;

    @Autowired 
    private CounterRepository counterRepo;

    public String saveNurse(Nurse nurse) {

        nurseRepo.save(nurse);
        return "Nurse saved successfully";
    }

    public String assignCounter(UUID nurseId, Integer counterNumber){
        Optional<Nurse>optionalNurse = nurseRepo.findById(nurseId);

        if(optionalNurse.isEmpty()){
            return "The nurse with that id does not exist";
        }
        Optional<Counter>optionalCounter = 
        counterRepo.findByCounterNumber(counterNumber);

        if(optionalCounter.isEmpty()){
            return "The counter with that number does not exist";
        }

        Nurse nurse = optionalNurse.get();
        Counter counter = optionalCounter.get();

        Optional<Nurse>assignedNurse =
        nurseRepo.findByCounter(counter);

        if(assignedNurse.isPresent()
        && !assignedNurse.get().getId().equals(nurseId)){
            return "That counter is taken by another nurse";
        }
        nurse.setCounter(counter);
        nurseRepo.save(nurse);

        return "Counter changed Successfully";
    }
    public String addServiceToNurse(UUID nurseId,UUID serviceId){
        Optional<Nurse>optionalNurse = nurseRepo.findById(nurseId);

        if(optionalNurse.isEmpty()){
            return "The nurse with that id does not exist";
        }

        Optional<hospital.hospital_queue_management_system.Entity.Service>optionalService = 
        serviceRepo.findById(serviceId);

        if(optionalService.isEmpty()){
            return "The Service with that id does not exist";
        }
        Nurse nurse = optionalNurse.get();
        hospital.hospital_queue_management_system.Entity.Service service = optionalService.get();

        if(nurse.getServices().contains(service)){
            return "The nurse already has that service";
        }

        nurse.getServices().add(service);
        nurseRepo.save(nurse);

        return "Service added to the nurse";
    }
    public List<Nurse>getAllNurses() {
        return nurseRepo.findAll();

    }
    public List<Nurse>getNursesByServiceName(String name){
        return nurseRepo.findNursesByServiceName(name);
    }

    public Optional<Nurse>getNurseById(UUID id){
        return nurseRepo.findById(id);
    }
    public String updateNurse(UUID id,Nurse updatedNurse){
        Optional<Nurse>result = nurseRepo.findById(id);

        if(result.isEmpty()) {
            return "The nurse with that id does not exist";
        }

        Nurse existing = result.get();

        existing.setFirstName(updatedNurse.getFirstName());
        existing.setLastName(updatedNurse.getLastName());
        existing.setStaffNumber(updatedNurse.getStaffNumber());

        nurseRepo.save(existing);
        return "Nurse updated successfully";
    }

    public String deleteNurse(UUID id) {
        if (!nurseRepo.existsById(id)){
            return "The nurse with that id does not exist";
        }

        nurseRepo.deleteById(id);
        return "Nurse deleted successfully";
    }

}
