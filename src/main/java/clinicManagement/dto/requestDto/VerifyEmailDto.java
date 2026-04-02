package clinicManagement.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyEmailDto {
    @NotBlank(message = "Email is can't be empty")
    @Email(message = "Invalid email address, please create an account with a valid email")
    private String email;
    @NotBlank(message = "Verification code is required, please check your email")
    private String verificationCode;

}
