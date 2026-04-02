package clinicManagement.service;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.TimeSlotResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface TimeSlotService {
    ResponseEntity<ApiResponse<?>> getTimeSlotsByDoctorId(UUID doctorId, LocalDate date);

}
