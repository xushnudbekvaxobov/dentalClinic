package clinicManagement.service;

import clinicManagement.dto.requestDto.WorkingTimeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public interface WorkingTimeService {
    ResponseEntity<ApiResponse<?>> addWorkingTime(WorkingTimeDto workingTimeDto, UUID doctorId);
    ResponseEntity<ApiResponse<?>> updateWorkingTime(WorkingTimeDto workingTimeDto,UUID doctorId);
    ResponseEntity<ApiResponse<?>> getPresentWeekWorkingTimeByDoctorId(UUID doctorId);
    ResponseEntity<ApiResponse<?>> getLastWeekWorkingTimeByDoctorId(UUID doctorId);
    ResponseEntity<ApiResponse<?>> getNextWeekWorkingTimeByDoctorId(UUID doctorId);
    ResponseEntity<ApiResponse<?>> getPresentMonthWorkingTimeByDoctorId(UUID doctorId);
    ResponseEntity<ApiResponse<?>> getLastMonthWorkingTimeByDoctorId(UUID doctorId);
    ResponseEntity<ApiResponse<?>> oneDayWorkingTimeByDoctorId(UUID doctorId, LocalDate workingDate);
}
