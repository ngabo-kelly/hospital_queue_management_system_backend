package hospital.hospital_queue_management_system.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hospital.hospital_queue_management_system.Entity.Service;


public interface ServiceRepository
extends JpaRepository<Service,UUID> {

    @Query("""
    SELECT s
    FROM Service s
    WHERE s.nurses IS EMPTY
    """)
    List<Service>findUnusedServices();



}
