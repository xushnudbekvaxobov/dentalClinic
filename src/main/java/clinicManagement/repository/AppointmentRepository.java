package clinicManagement.repository;

import clinicManagement.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {
    Boolean existsByDoctorEntity_IdAndAppointmentDateAndStartTime(Long doctorEntityId, LocalDate appointmentDate, LocalTime startTime);
    List<AppointmentEntity> findAllByDoctorEntity_IdAndAppointmentDate(Long doctorEntityId, LocalDate appointmentDate);

    List<AppointmentEntity> findAllByDoctorEntity_Id(Long doctorId);
}
