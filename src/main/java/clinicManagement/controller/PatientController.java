package clinicManagement.controller;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    @PostMapping("/create-patient")
    public ResponseEntity<ApiResponse<?>> addPatient(@RequestBody PatientDto patientDto,
                                                     @RequestParam Long userId) {
        return patientService.addPatient(patientDto,userId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody PatientDto patientDto, Long userId) {
        return patientService.updatePatient(patientDto, userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<?>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size) {
        return patientService.getAll(page, size);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")

    public ResponseEntity<ApiResponse<?>> search(@RequestParam(required = false) String fullName,
                                                 @RequestParam(required = false) String phone,
                                                 @RequestParam(required = false) String address) {
        return patientService.search(fullName, phone, address);
    }
}
