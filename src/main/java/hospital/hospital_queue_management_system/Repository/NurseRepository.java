package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.Nurse;
import hospital.hospital_queue_management_system.Entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NurseRepository extends JpaRepository<Nurse, UUID> {

    Optional<Nurse>findById(UUID id);
    Optional<Nurse>findByCounter(Counter counter);
    @Query("""
            SELECT DISTINCT n
            FROM Nurse n
            JOIN n.services s
            WHERE LOWER(s.name) = LOWER(:name)""")
    List<Nurse> findNursesByServiceName(@Param("name") String name);
}
