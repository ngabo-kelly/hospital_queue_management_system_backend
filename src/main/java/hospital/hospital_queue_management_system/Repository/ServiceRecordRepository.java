package hospital.hospital_queue_management_system.Repository;

import hospital.hospital_queue_management_system.Entity.ServiceRecord;
import hospital.hospital_queue_management_system.Entity.QueueTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface ServiceRecordRepository 
extends JpaRepository<ServiceRecord,UUID>{

    

    @Query("""
        SELECT sr
        FROM ServiceRecord sr
        WHERE sr.queueTicket.outpatient.fileNumber = :fileNumber
        ORDER BY sr.recordedDate DESC
        """)
        List<ServiceRecord>findHistoryByFileNumber(
            @Param("fileNumber")String fileNumber
        );

        boolean existsByQueueTicket(QueueTicket queueTicket);
}

