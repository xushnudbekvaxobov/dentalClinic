package clinicManagement.dto.responseDto;

import clinicManagement.util.enums.EmployeeStatus;
import clinicManagement.util.enums.EmployeeType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {
    public Long id;
    public String fullName;
    public LocalDate birthDate;
    public String gender;
    public String phone;
    public String address;
    public LocalDate createdAt;
    private EmployeeType employeeType;
    private LocalDate updatedAt;
    private EmployeeStatus employeeStatus;
}
