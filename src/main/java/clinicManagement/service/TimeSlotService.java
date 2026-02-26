package clinicManagement.service;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.TimeSlotResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TimeSlotService {
    ResponseEntity<ApiResponse<?>> getTimeSlotsByDoctorId(Long doctorId, LocalDate date);

}
