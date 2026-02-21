package clinicManagement.service;

import clinicManagement.dto.requestDto.LoginDto;
import clinicManagement.dto.requestDto.UserDto;
import clinicManagement.dto.requestDto.VerifyEmailDto;
import clinicManagement.dto.responseDto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<ApiResponse<?>> register(UserDto userDto);
    ResponseEntity<ApiResponse<?>> login(LoginDto loginDto);
    ResponseEntity<ApiResponse<?>> verifyEmail(VerifyEmailDto verifyEmailDto);
    ResponseEntity<ApiResponse<?>> sendVerificationCodeForForgotPassword(String email);
    ResponseEntity<ApiResponse<?>> checkVerificationCodeForForgotPassword(VerifyEmailDto verifyEmailDto);
    ResponseEntity<ApiResponse<?>> changePassword(String email, String newPassword);
    ResponseEntity<ApiResponse<?>> findById(Long id);
}
