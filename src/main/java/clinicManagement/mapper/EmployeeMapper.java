package clinicManagement.mapper;

import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.EmployeeResponseDto;
import clinicManagement.dto.responseDto.UserResponseDto;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.util.enums.EmployeeStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class EmployeeMapper {
    private final UserMapper userMapper;

    public EmployeeMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto, UserEntity userEntity) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setUserEntity(userEntity);
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setBirthDate(employeeDto.getBirthDate());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setPhone(employeeDto.getPhone());
        employeeEntity.setAddress(employeeDto.getAddress());
        employeeEntity.setSpeciality(employeeDto.getSpeciality());
        employeeEntity.setEmployeeType(employeeDto.getEmployeeType());
        employeeEntity.setCreatedAt(LocalDateTime.now());
        employeeEntity.setUpdatedAt(LocalDateTime.now());
        employeeEntity.setEmployeeStatus(EmployeeStatus.ACTIVE);
        return employeeEntity;
    }


    public EmployeeResponseDto toEmployeeResponseDto(EmployeeEntity employeeEntity) {
        UserResponseDto userResponseDto = null;
        if(employeeEntity.getUserEntity() != null) {
            userResponseDto = userMapper.toDto(employeeEntity.getUserEntity());
        }
        return EmployeeResponseDto.builder()
                .id(employeeEntity.getId())
                .firstName(employeeEntity.getFirstName())
                .lastName(employeeEntity.getLastName())
                .birthDate(employeeEntity.getBirthDate())
                .gender(employeeEntity.getGender())
                .phone(employeeEntity.getPhone())
                .address(employeeEntity.getAddress())
                .createdAt(employeeEntity.getCreatedAt())
                .employeeType(employeeEntity.getEmployeeType())
                .updatedAt(employeeEntity.getUpdatedAt())
                .employeeStatus(employeeEntity.getEmployeeStatus())
                .userResponseDto(userResponseDto)
                .build();
    }
}
