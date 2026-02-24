package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.DoctorDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.entity.DoctorEntity;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.DoctorMapper;
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
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(EmployeeRepository employeeRepository, DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.employeeRepository = employeeRepository;
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> createDoctor(DoctorDto doctorDto,Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow(()->new DataNotFoundException("employee not found"));
        if(doctorRepository.existsByEmployeeEntity_Id(employeeId)){
            throw new AppBadException("doctor already created");
        }
        doctorRepository.save(doctorMapper.toDoctorEntity(doctorDto,employeeEntity));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("doctor created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateDoctor(DoctorDto doctorDto,Long employeeId) {
        if(employeeRepository.existsById(employeeId)){
            throw new AppBadException("employee not found");
        }
        DoctorEntity doctorEntity = doctorRepository.findByEmployeeEntity_Id(employeeId).orElseThrow(()->new DataNotFoundException("employee not found"));
        doctorEntity.setSpeciality(doctorDto.getSpeciality());
        doctorEntity.setLicenseNumber(doctorDto.getLicenseNumber());
        doctorEntity.setExperienceYear(doctorDto.getExperienceYear());
        doctorRepository.save(doctorEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("doctor created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> changeStatus(DoctorStatus status,Long employeeId) {
        if(employeeRepository.existsById(employeeId)){
            throw new AppBadException("employee not found");
        }
        DoctorEntity doctorEntity = doctorRepository.findByEmployeeEntity_Id(employeeId).orElseThrow(()->new DataNotFoundException("employee not found"));
        doctorEntity.setStatus(status);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("doctor created successfully",true,null,201));
    }
}
