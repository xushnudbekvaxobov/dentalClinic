package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.PatientDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.entity.PatientEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.PatientMapper;
import clinicManagement.repository.PatientRepository;
import clinicManagement.repository.UserRepository;
import clinicManagement.service.PatientService;
import clinicManagement.util.specification.PatientSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<?>> addPatient(PatientDto patientDto,Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(()-> new DataNotFoundException("user not found"));
        patientRepository.save(patientMapper.toEntity(patientDto,user));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("patient created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updatePatient(PatientDto patientDto, Long id) {
       PatientEntity patientEntity = patientRepository.findById(id).orElseThrow(() -> new DataNotFoundException("patient not found"));
            patientEntity.setFullName(patientDto.getFullName());
            patientEntity.setBirthDate(patientDto.getBirthDate());
            patientEntity.setGender(patientDto.getGender());
            patientEntity.setPhone(patientDto.getPhone());
            patientEntity.setAddress(patientDto.getAddress());
            patientEntity.setCreatedAt(LocalDate.now());
            patientEntity.setAllergies(patientDto.getAllergies());
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
