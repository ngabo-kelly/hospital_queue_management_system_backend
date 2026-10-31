package hospital.hospital_queue_management_system.Repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import hospital.hospital_queue_management_system.Entity.Outpatient;

public interface OutpatientRepository
extends JpaRepository<Outpatient,UUID> {
    List<Outpatient>findByLastNameIgnoreCaseOrderByFirstNameAsc( 
        String lastName
    );
    
}





