package clinicManagement.repository;

import clinicManagement.entity.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface VisitRepository extends JpaRepository<VisitEntity, UUID> {
    @Query("select max(v.queueNumber) from VisitEntity v where v.visitDate = :visitDate")
    Integer findMaxRowQueueNumberByVisitDate(@Param("visitDate") LocalDate visitDate);
}
