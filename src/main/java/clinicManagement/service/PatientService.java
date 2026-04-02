package clinicManagement.service;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PatientService {
    ResponseEntity<ApiResponse<?>> addPatient(PatientDto patientDto, UUID userId);
    ResponseEntity<ApiResponse<?>> updatePatient(PatientDto patientDto, UUID id);
    ResponseEntity<ApiResponse<?>> getAll(Integer page,Integer size);
    ResponseEntity<ApiResponse<?>> getById(UUID id);
    ResponseEntity<ApiResponse<?>> search(String fullName, String phone, String address);
}
