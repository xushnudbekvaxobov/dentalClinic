package clinicManagement.repository;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.entity.WorkingTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkingTimeRepository extends JpaRepository<WorkingTimeEntity,Long> {
    Optional<WorkingTimeEntity> findByDoctorEntity_IdAndDayOfWeekAndIsExpired(Long doctorId, DayOfWeek dayOfWeek, Boolean isExpired);

    Boolean existsByDoctorEntity_IdAndWorkingDateAndIsExpired(Long doctorId, LocalDate workingDate, Boolean isExpired);

    Optional<WorkingTimeEntity> findByDoctorEntity_IdAndWorkingDateAndIsExpired(Long doctorId, LocalDate workingDate, Boolean isExpired);
    List<WorkingTimeEntity> findAllByDoctorEntity_IdAndWorkingDateBetween(Long doctorEntity_id,LocalDate startDate, LocalDate endDate);
    List<WorkingTimeEntity> findAllByWorkingDateBetweenOrderByDoctorEntity(LocalDate workingDateAfter, LocalDate workingDateBefore);

}
