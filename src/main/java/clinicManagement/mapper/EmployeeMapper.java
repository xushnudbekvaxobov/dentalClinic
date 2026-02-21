package clinicManagement.mapper;

import clinicManagement.dto.requestDto.EmployeeDto;
import clinicManagement.entity.EmployeeEntity;
import clinicManagement.entity.UserEntity;
import clinicManagement.util.enums.EmployeeStatus;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class EmployeeMapper {
    public EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto, UserEntity userEntity){
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
}
