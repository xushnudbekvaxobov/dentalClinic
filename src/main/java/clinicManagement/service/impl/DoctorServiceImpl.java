package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.DoctorDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.repository.DoctorRepository;
import clinicManagement.repository.EmployeeRepository;
import clinicManagement.service.DoctorService;
import clinicManagement.util.enums.DoctorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final EmployeeRepository employeeRepository;
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(EmployeeRepository employeeRepository, DoctorRepository doctorRepository) {
        this.employeeRepository = employeeRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> createDoctor(DoctorDto doctorDto,Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(() -> new DataNotFoundException("employee not found"));
        if(doctorRepository.existsByEmployeeEntity_Id(employeeId)){
            throw new AppBadException("doctor data already created");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("doctor created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateDoctor(DoctorDto doctorDto,Long employeeId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("doctor created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> changeStatus(DoctorStatus status,Long employeeId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("doctor created successfully",true,null,201));
    }
}
