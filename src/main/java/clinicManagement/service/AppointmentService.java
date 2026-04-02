package clinicManagement.service;

import clinicManagement.dto.requestDto.AppointmentDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public interface AppointmentService {
    ResponseEntity<ApiResponse<?>> booking(AppointmentDto appointmentDto, UUID patientId, UUID doctorId);
    ResponseEntity<ApiResponse<?>> getAllAppointmentsByDoctorId(UUID doctorId);
    ResponseEntity<ApiResponse<?>> getAllAppointmentsByDoctorIdAndDate(UUID doctorId, LocalDate date);
}
