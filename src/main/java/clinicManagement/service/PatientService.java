package clinicManagement.service;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {
    ResponseEntity<ApiResponse<?>> addPatient(PatientDto patientDto,Long userId);
    ResponseEntity<ApiResponse<?>> updatePatient(PatientDto patientDto,Long id);
    ResponseEntity<ApiResponse<?>> getAll(Integer page,Integer size);
    ResponseEntity<ApiResponse<?>> getById(Long id);
    ResponseEntity<ApiResponse<?>> search(String fullName, String phone, String address);
}
