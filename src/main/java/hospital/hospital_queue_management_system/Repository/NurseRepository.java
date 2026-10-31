package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NurseRepository extends JpaRepository<Nurse, UUID> {

    @Query("""
        SELECT DISTINCT N
        FROM Nurse n
        JOIN n.services s
        WHERE LOWER(s.name) = LOWER(:name)""")
        List<Nurse> findNursesByServiceName(@Param("name")String name);
}
