package clinicManagement.controller;

import clinicManagement.dto.requestDto.DoctorDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.DoctorService;
import clinicManagement.util.enums.DoctorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<?>> create(DoctorDto doctorDto,Long employeeId){
        return doctorService.createDoctor(doctorDto,employeeId);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> update(DoctorDto doctorDto,Long employeeId){
        return doctorService.updateDoctor(doctorDto,employeeId);
    }


    @PutMapping("/change-status/{id}/{status}")
    public ResponseEntity<ApiResponse<?>> changeStatus(@PathVariable Long id, @PathVariable DoctorStatus status){
        return doctorService.changeStatus(status,id);
    }
}
