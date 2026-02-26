package clinicManagement.mapper;

import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.dto.responseDto.EmployeeResponseDto;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.util.enums.EmployeeStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class EmployeeMapper {
    public EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto, UserEntity userEntity) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setUser(userEntity);
        employeeEntity.setFullName(employeeDto.getFullName());
        employeeEntity.setBirthDate(employeeDto.getBirthDate());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setPhone(employeeDto.getPhone());
        employeeEntity.setAddress(employeeDto.getAddress());
        employeeEntity.setEmployeeType(employeeDto.getEmployeeType());
        employeeEntity.setCreatedAt(LocalDate.now());
        employeeEntity.setUpdatedAt(LocalDate.now());
        employeeEntity.setEmployeeStatus(EmployeeStatus.ACTIVE);
        return employeeEntity;
    }

    public EmployeeResponseDto toEmployeeResponseDto(EmployeeEntity employeeEntity) {
        return EmployeeResponseDto.builder()
                .id(employeeEntity.getId())
                .fullName(employeeEntity.getFullName())
                .birthDate(employeeEntity.getBirthDate())
                .gender(employeeEntity.getGender())
                .phone(employeeEntity.getPhone())
                .address(employeeEntity.getAddress())
                .createdAt(employeeEntity.getCreatedAt())
                .employeeType(employeeEntity.getEmployeeType())
                .updatedAt(employeeEntity.getUpdatedAt())
                .employeeStatus(employeeEntity.getEmployeeStatus())
                .build();
    }
}
