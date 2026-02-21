package clinicManagement.repository;

import clinicManagement.entity.WorkingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.Optional;

public interface WorkingTimeRepository extends JpaRepository<WorkingTimeEntity,Long> {
    Optional<WorkingTimeEntity> findByDoctorEntity_IdAndDayOfWeekAndIsExpired(Long doctorId, DayOfWeek dayOfWeek, Boolean isExpired);
    Boolean existByDoctorEntity_IdAndDayOfWeekAndIsExpired(Long doctorId, DayOfWeek dayOfWeek, Boolean isExpired);
}
