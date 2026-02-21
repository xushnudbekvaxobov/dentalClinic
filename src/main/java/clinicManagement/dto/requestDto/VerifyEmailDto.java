package clinicManagement.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailDto {
    @NotBlank(message = "email is can't be empty")
    @Email(message = "invalid email address")
    private String email;
    @Size(min = 5,max = 5,message = "verificationCode should be 5 symbols")
    private String verificationCode;

}
