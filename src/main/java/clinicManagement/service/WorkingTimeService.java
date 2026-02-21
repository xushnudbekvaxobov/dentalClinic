package clinicManagement.service;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface WorkingTimeService {
    ResponseEntity<ApiResponse<?>> addWorkingTimeWithDoctorId(WorkingTimeDto workingTimeDto, Long doctorId);
    ResponseEntity<ApiResponse<?>> updateWorkingTimeWithDoctorId(WorkingTimeDto workingTimeDto,Long doctorId);
}
