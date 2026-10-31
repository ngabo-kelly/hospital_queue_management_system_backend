package hospital.hospital_queue_management_system.Repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import hospital.hospital_queue_management_system.Entity.Counter;
public interface CounterRepository extends JpaRepository<Counter,UUID> {

    Optional<Counter>findByCounterNumber(Integer counterNumber);
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
