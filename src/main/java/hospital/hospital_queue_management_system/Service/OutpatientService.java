package hospital.hospital_queue_management_system.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.hospital_queue_management_system.Entity.Outpatient;
import hospital.hospital_queue_management_system.Repository.OutpatientRepository;

@Service
public class OutpatientService {

    @Autowired
    private OutpatientRepository outpatientRepository;

    public String saveOutpatient(Outpatient outpatient){
        outpatientRepository.save(outpatient);
        return "Outpatient saved successfully";
    }
    public List<Outpatient>getAllOutpatients(){
        return outpatientRepository.findAll();
    }
    public List<Outpatient>getOutpatientByLastName(String lastName){
        return outpatientRepository
        .findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
    
    public Optional<Outpatient>getOutpatientById(UUID id){
        return outpatientRepository.findById(id);
    }
    public String updateOutpatient(UUID id,Outpatient updated){
        Optional<Outpatient>result = outpatientRepository.findById(id);

        if(result.isEmpty()){
            return "The outpatient with that id does not exist";
        }
        Outpatient outpatient = result.get();
        outpatient.setFirstName(updated.getFirstName());
        outpatient.setLastName(updated.getLastName());
        outpatient.setFileNumber(updated.getFileNumber());
        outpatient.setPhone(updated.getPhone());
        outpatient.setInsurance(updated.getInsurance());

        outpatientRepository.save(outpatient);
        return "Outpatient updated successfully";
    }

    public String deleteOutpatient(UUID id){
        if(!outpatientRepository.existsById(id)){
            return "The outpatient with that id does not exist";
        }

        outpatientRepository.deleteById(id);
        return "Outpatient deleted successfully";
    }

}
