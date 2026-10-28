package hospital.hospital_queue_management_system.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import hospital.hospital_queue_management_system.Entity.Counter;
public interface CounterRepository extends JpaRepository<Counter,UUID> {

}
