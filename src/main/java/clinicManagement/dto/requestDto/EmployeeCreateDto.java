package clinicManagement.dto.requestDto;

import clinicManagement.util.enums.EmployeeType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeCreateDto {
    @NotNull(message = "Employee details must be created!")
    private EmployeeDto employeeDto;
    private UserEmployeeDto userEmployeeDto;
//    private String email;
//    private String password;
}
