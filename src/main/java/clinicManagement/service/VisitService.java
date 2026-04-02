package clinicManagement.service;

import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface VisitService {
    ResponseEntity<ApiResponse<?>> createVisit(UUID patientId, UUID doctorId);
}
