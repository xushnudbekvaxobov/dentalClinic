package clinicManagement.service;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface WorkingTimeService {
    ResponseEntity<ApiResponse<?>> addWorkingTime(WorkingTimeDto workingTimeDto, Long doctorId);
    ResponseEntity<ApiResponse<?>> updateWorkingTime(WorkingTimeDto workingTimeDto,Long doctorId);
    ResponseEntity<ApiResponse<?>> getPresentWeekWorkingTimeByDoctorId(Long doctorId);
    ResponseEntity<ApiResponse<?>> getLastWeekWorkingTimeByDoctorId(Long doctorId);
    ResponseEntity<ApiResponse<?>> getNextWeekWorkingTimeByDoctorId(Long doctorId);
}
