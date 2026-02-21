package clinicManagement.dto.requestDto;

import clinicManagement.util.enums.EmployeeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeCreateDto {
    private EmployeeDto employeeDto;
    private UserEmployeeDto userEmployeeDto;
//    private String email;
//    private String password;
}
