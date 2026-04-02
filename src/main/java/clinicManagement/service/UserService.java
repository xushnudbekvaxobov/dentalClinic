package clinicManagement.service;

import clinicManagement.dto.requestDto.LoginDto;
import clinicManagement.dto.requestDto.ResetPasswordDto;
import clinicManagement.dto.requestDto.UserDto;
import clinicManagement.dto.requestDto.VerifyEmailDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.JwtResponseDto;
import clinicManagement.dto.responseDto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    void register(UserDto userDto);
    JwtResponseDto login(LoginDto loginDto);
    void verifyEmail(VerifyEmailDto verifyEmailDto);
    void sendVerificationCodeForForgotPassword(String email);
    void resetPassword(ResetPasswordDto resetPasswordDto);
    ResponseEntity<ApiResponse<?>> findById(UUID id);

}
