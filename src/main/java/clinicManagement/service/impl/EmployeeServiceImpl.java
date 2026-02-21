package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.EmployeeCreateDto;
import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.EmployeeMapper;
import clinicManagement.mapper.UserMapper;
import clinicManagement.repository.EmployeeRepository;
import clinicManagement.repository.UserRepository;
import clinicManagement.service.EmailService;
import clinicManagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper,
                               EmployeeRepository employeeRepository,
                               UserRepository userRepository,
                               UserMapper userMapper,
                               EmailService emailService) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.emailService = emailService;
    }

    @Transactional
    @Override
    public ResponseEntity<ApiResponse<?>> createEmployee(EmployeeCreateDto employeeCreateDto) {
        String verificationCode = EmailServiceImpl.generateVerificationCode();
        UserEntity userEntity = userRepository.save(userMapper.toUserEntityForEmployee(employeeCreateDto.getUserEmployeeDto(),verificationCode));
        employeeRepository.save(employeeMapper.toEmployeeEntity(employeeCreateDto.getEmployeeDto(),userEntity));
        emailService.sendVerificationCode(employeeCreateDto.getUserEmployeeDto().getEmail(),verificationCode);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>("employee created successfully",true,null,201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllEmployee() {
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(new ApiResponse<>("getting all employee",true,employeeEntity,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new DataNotFoundException("employee not found"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("getting employee by id",true,employeeEntity,200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateEmployee(EmployeeDto employeeDto, Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new DataNotFoundException("employee not found"));
        employeeEntity.setFullName(employeeDto.getFullName());
        employeeEntity.setBirthDate(employeeDto.getBirthDate());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setPhone(employeeDto.getPhone());
        employeeEntity.setAddress(employeeDto.getAddress());
        employeeRepository.save(employeeEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("employee updated successfully",true,null,200));
    }

}
