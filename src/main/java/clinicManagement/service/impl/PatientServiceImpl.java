package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.entity.PatientEntity;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.PatientMapper;
import clinicManagement.repository.PatentRepository;
import clinicManagement.service.PatientService;
import clinicManagement.util.enums.specification.PatientSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatentRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatentRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> addPatient(PatientDto patientDto) {
       patientRepository.save(patientMapper.toPatientEntity(patientDto));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("patient created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updatePatient(PatientDto patientDto, Long id) {
       PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new DataNotFoundException("patient not found"));
        patientMapper.toPatientEntityForUpdate(patientEntity,patientDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("patient update successfully",true,null,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAll(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<PatientEntity> patientPage = patientRepository.findAll(pageRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("patients getting successfully",true,patientPage.getContent(),200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getById(Long id) {
        PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new DataNotFoundException("patient not found"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("patient getting successfully",true,patientEntity,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> search(String fullName, String phone, String address) {
        Specification<PatientEntity> patientSpecification = Specification.where(PatientSpecification.hasFullName(fullName))
                .and(PatientSpecification.hasPhone(phone)
                        .and(PatientSpecification.hasAddress(address)
                        ));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("patients getting successfully",true,patientRepository.findAll(patientSpecification),200));
    }

}
