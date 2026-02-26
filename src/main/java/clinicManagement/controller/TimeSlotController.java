package clinicManagement.controller;

import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.TimeSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/slot")
@RequiredArgsConstructor
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @GetMapping("/get-slots")
    public ResponseEntity<ApiResponse<?>> getSlots(@RequestParam Long doctorId,@RequestParam LocalDate date){
        return timeSlotService.getTimeSlotsByDoctorId(doctorId,date);
    }
}
