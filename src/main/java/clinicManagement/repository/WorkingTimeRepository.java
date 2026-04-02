package clinicManagement.repository;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.entity.WorkingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkingTimeRepository extends JpaRepository<WorkingTimeEntity, UUID> {
    Optional<WorkingTimeEntity> findByDoctorEntity_IdAndDayOfWeekAndIsExpired(UUID doctorId, DayOfWeek dayOfWeek, Boolean isExpired);

    Boolean existsByDoctorEntity_IdAndWorkingDateAndIsExpired(UUID doctorId, LocalDate workingDate, Boolean isExpired);

    Optional<WorkingTimeEntity> findByDoctorEntity_IdAndWorkingDateAndIsExpired(UUID doctorId, LocalDate workingDate, Boolean isExpired);
    List<WorkingTimeEntity> findAllByDoctorEntity_IdAndWorkingDateBetween(UUID doctorEntity_id,LocalDate startDate, LocalDate endDate);
    List<WorkingTimeEntity> findAllByWorkingDateBetweenOrderByDoctorEntity_Id(LocalDate workingDateAfter, LocalDate workingDateBefore);
    Optional<WorkingTimeEntity> findByDoctorEntity_IdAndWorkingDate(UUID doctorEntityId,LocalDate workingDate);

    Optional<WorkingTimeEntity> findByDoctorEntity_Id(UUID doctorEntityId);
}
