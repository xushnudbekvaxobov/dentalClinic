package clinicManagement.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "email is can't be empty")
    @Email(message = "invalid email address")
    private String email;
    @NotBlank(message = "password is can't be empty")
    @Size(min = 8,max = 8,message = "password should be 8 numbers")
    private String password;
}
