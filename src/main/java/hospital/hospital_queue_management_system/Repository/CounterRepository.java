package hospital.hospital_queue_management_system.Repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import hospital.hospital_queue_management_system.Entity.Counter;
public interface CounterRepository extends JpaRepository<Counter,UUID> {

    @Query("""
        SELECT c
        FROM Counter c
        WHERE NOT EXISTS(
        SELECT n 
        FROM Nurse n
        WHERE n.counter = c)
        ORDER BY c.counterNumber ASC
        """)
        List<Counter>findFreeCounter();
}
