package clinicManagement.dto.responseDto;

import clinicManagement.util.enums.EmployeeStatus;
import clinicManagement.util.enums.EmployeeType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {
    public UUID id;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public String gender;
    public String phone;
    public String address;
    public LocalDateTime createdAt;
    private EmployeeType employeeType;
    private LocalDateTime updatedAt;
    private EmployeeStatus employeeStatus;
    private UserResponseDto userResponseDto;
}
