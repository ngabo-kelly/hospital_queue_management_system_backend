package hospital.hospital_queue_management_system.Repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import hospital.hospital_queue_management_system.Entity.Outpatient;

public interface OutpatientRepository
extends JpaRepository<Outpatient,UUID> {
    List<Outpatient>findByLastNameIgnoreCaseOrderByFirstNameAsc( 
        String lastName
    );
   @Query("""
    SELECT DISTINCT q.outpatient
    FROM QueueTicket q
    WHERE q.nurse.id = :nurseId
    """)
    List<Outpatient>findOutpatientByNurseId(
        @Param("nurseId")UUID nurseId);
    
}





