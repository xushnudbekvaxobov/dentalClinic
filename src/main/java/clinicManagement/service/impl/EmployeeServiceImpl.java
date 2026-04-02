package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.EmployeeCreateDto;
import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.EmployeeResponseDto;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.mapper.EmployeeMapper;
import clinicManagement.mapper.UserMapper;
import clinicManagement.repository.EmployeeRepository;
import clinicManagement.repository.UserRepository;
import clinicManagement.service.EmailService;
import clinicManagement.service.EmployeeService;
import clinicManagement.util.enums.EmployeeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper,
                               EmployeeRepository employeeRepository,
                               UserRepository userRepository,
                               UserMapper userMapper) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public EmployeeResponseDto createEmployee(EmployeeCreateDto employeeCreateDto) {
        if (employeeCreateDto.getEmployeeDto().getEmployeeType().equals(EmployeeType.DOCTOR) && employeeCreateDto.getUserEmployeeDto() == null) {
            throw new AppBadException("Doctor must have user account!");
        }
        if (employeeCreateDto.getEmployeeDto().getEmployeeType().equals(EmployeeType.DOCTOR)) {
            UserEntity savedUser = userRepository.save(userMapper.toUserEntityForEmployee(employeeCreateDto.getUserEmployeeDto()));
            EmployeeEntity savedEmployee = employeeRepository.save(employeeMapper.toEmployeeEntity(employeeCreateDto.getEmployeeDto(), savedUser));
            return employeeMapper.toEmployeeResponseDto(savedEmployee);
        } else {
            EmployeeEntity savedEmployee = employeeRepository.save(employeeMapper.toEmployeeEntity(employeeCreateDto.getEmployeeDto(), null));
            return employeeMapper.toEmployeeResponseDto(savedEmployee);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> getAllEmployee() {
        List<EmployeeEntity> employeeEntity = employeeRepository.findAll();
        List<EmployeeResponseDto> employeeResponseDto = employeeEntity.stream().map(employeeMapper::toEmployeeResponseDto).toList();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(new ApiResponse<>("getting all employee",true,employeeResponseDto,200));
    }


    @Override
    public ResponseEntity<ApiResponse<?>> getEmployeeById(UUID id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new DataNotFoundException("employee not found"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("getting employee by id",true,employeeMapper.toEmployeeResponseDto(employeeEntity),200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> updateEmployee(EmployeeDto employeeDto, UUID id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new DataNotFoundException("employee not found"));
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
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
