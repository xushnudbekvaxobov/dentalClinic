package clinicManagement.controller;

import clinicManagement.dto.requestDto.LoginDto;
import clinicManagement.dto.requestDto.UserDto;
import clinicManagement.dto.requestDto.VerifyEmailDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody @Valid UserDto userDto){
        return userService.register(userDto);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginDto loginDto){
        return userService.login(loginDto);
    }
    @PutMapping("/email-verify")
    public ResponseEntity<ApiResponse<?>> verifyEmail(@RequestBody @Valid VerifyEmailDto verifyEmailDto) {
        return userService.verifyEmail(verifyEmailDto);
    }
    @PutMapping("/forget-password")
    public ResponseEntity<ApiResponse<?>> sendVerificationCodeForForgotPassword(@RequestParam @Valid String email) {
            return userService.sendVerificationCodeForForgotPassword(email);
    }
    @PostMapping("/check-verify-code")
    public ResponseEntity<ApiResponse<?>> checkVerificationCode(@RequestBody @Valid VerifyEmailDto verifyEmailDto) {
        return userService.checkVerificationCodeForForgotPassword(verifyEmailDto);
    }

}
