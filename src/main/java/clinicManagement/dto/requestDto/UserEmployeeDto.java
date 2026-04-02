package clinicManagement.dto.requestDto;

import clinicManagement.util.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEmployeeDto {
    @NotBlank(message = "Email is can't be empty!")
    @Email(message = "Invalid email address!")
    private String email;
    @NotBlank(message = "Password is can't be empty!")
    @Size(min = 8,max = 10,message = "Password should be between 8 and 10 characters!")
    private String password;
    private UserRole role;
}
