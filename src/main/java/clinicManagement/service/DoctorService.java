package clinicManagement.service;

import clinicManagement.dto.requestDto.DoctorDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.util.enums.DoctorStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DoctorService {
    ResponseEntity<ApiResponse<?>> createDoctor(DoctorDto doctorDto, UUID employeeId);
    ResponseEntity<ApiResponse<?>> updateDoctor(DoctorDto doctorDto, UUID employeeId);
    ResponseEntity<ApiResponse<?>> changeStatus(DoctorStatus status, UUID employeeId);
}
