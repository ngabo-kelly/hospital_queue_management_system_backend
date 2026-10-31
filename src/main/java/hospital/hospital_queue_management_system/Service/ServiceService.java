package hospital.hospital_queue_management_system.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hospital.hospital_queue_management_system.Repository.ServiceRepository;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public String saveService(hospital.hospital_queue_management_system.Entity.Service service){
        serviceRepository.save(service);
        return "Service saved Successfully";
    }

    public List<hospital.hospital_queue_management_system.Entity.Service>getAllServices(){
        return serviceRepository.findAll();
    }
    
    public List<hospital.hospital_queue_management_system.Entity.Service>
    getUnusedServices(){
        return serviceRepository.findUnusedServices();
    }

    public Optional<hospital.hospital_queue_management_system.Entity.Service>getServiceById(UUID id){
        return serviceRepository.findById(id);
    }

    public String updateService(UUID id,hospital.hospital_queue_management_system.Entity.Service updated){
        Optional<hospital.hospital_queue_management_system.Entity.Service>result = serviceRepository.findById(id);

        if(result.isEmpty()){
            return "The service with that id does not exist";
        }
        hospital.hospital_queue_management_system.Entity.Service service = result.get();
        service.setName(updated.getName());

        serviceRepository.save(service);
        return"Service Updated Successfully";
    }

    public String deleteService(UUID id){
        if (!serviceRepository.existsById(id)){
            return "The service with that id does not exist";
        }
        serviceRepository.deleteById(id);
        return "Service Deleted Successfully";
    }

}
