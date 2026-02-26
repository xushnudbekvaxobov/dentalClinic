package clinicManagement.service;

import clinicManagement.dto.requestDto.AppointmentDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface AppointmentService {
    ResponseEntity<ApiResponse<?>> booking(AppointmentDto appointmentDto,Long patientId,Long doctorId);
    ResponseEntity<ApiResponse<?>> getAllAppointmentsByDoctorId(Long doctorId);
    ResponseEntity<ApiResponse<?>> getAllAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate date);
}
