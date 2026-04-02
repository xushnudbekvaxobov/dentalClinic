package clinicManagement.repository;

import clinicManagement.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, UUID> {
    Boolean existsByDoctorEntity_IdAndAppointmentDateAndStartTime(UUID doctorEntityId, LocalDate appointmentDate, LocalTime startTime);
    List<AppointmentEntity> findAllByDoctorEntity_IdAndAppointmentDate(UUID doctorEntityId, LocalDate appointmentDate);

    List<AppointmentEntity> findAllByDoctorEntity_Id(UUID doctorId);
}
