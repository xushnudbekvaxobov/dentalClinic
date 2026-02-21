package clinicManagement.dto.requestDto;

import clinicManagement.util.enums.EmployeeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    private String fullName;
    private LocalDate birthDate;
    private String gender;
    private String phone;
    private String address;
    private EmployeeType employeeType;
}
