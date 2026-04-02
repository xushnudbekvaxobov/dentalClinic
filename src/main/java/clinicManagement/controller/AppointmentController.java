package clinicManagement.controller;

import clinicManagement.dto.requestDto.AppointmentDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/booking")
    public ResponseEntity<ApiResponse<?>> booking(@RequestBody AppointmentDto appointmentDto,
                                                  @RequestParam UUID patientId,
                                                  @RequestParam UUID doctorId){
        return appointmentService.booking(appointmentDto,patientId,doctorId);
    }

    @PutMapping("/change-booking")
    public ResponseEntity<ApiResponse<?>> changeBooking(@RequestBody AppointmentDto appointmentDto,
                                                  @RequestParam UUID patientId,
                                                  @RequestParam UUID doctorId){
        return appointmentService.booking(appointmentDto,patientId,doctorId);
    }

    @GetMapping("/get-appointments")
    public ResponseEntity<ApiResponse<?>> getAppointmentsByDoctorId(@RequestParam UUID doctorId) {
        return appointmentService.getAllAppointmentsByDoctorId(doctorId);
    }
}
