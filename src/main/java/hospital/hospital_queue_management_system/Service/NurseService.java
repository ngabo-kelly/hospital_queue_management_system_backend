package hospital.hospital_queue_management_system.Service;

import hospital.hospital_queue_management_system.Entity.Nurse;
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

    public String saveNurse(Nurse nurse) {

        nurseRepo.save(nurse);
        return "Nurse saved successfully";
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
