package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface NurseRepository extends JpaRepository<Nurse, UUID> {

}
