package clinicManagement.controller;

import clinicManagement.dto.requestDto.LoginDto;
import clinicManagement.dto.requestDto.ResetPasswordDto;
import clinicManagement.dto.requestDto.UserDto;
import clinicManagement.dto.requestDto.VerifyEmailDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
         userService.register(userDto);
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(new ApiResponse<>("successfully", true, null, 200));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginDto loginDto){
         return ResponseEntity
                 .status(HttpStatus.OK)
                 .body(new ApiResponse<>("successfully", true,  userService.login(loginDto), 200));
    }

    @PutMapping("/verify-email")
    public ResponseEntity<ApiResponse<?>> verifyEmail(@RequestBody @Valid VerifyEmailDto verifyEmailDto) {
         userService.verifyEmail(verifyEmailDto);
         return  ResponseEntity
                 .status(HttpStatus.OK)
                 .body(new ApiResponse<>("successfully", true, null, 200));
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<ApiResponse<?>> sendVerificationCodeForForgotPassword(@RequestParam String email) {
             userService.sendVerificationCodeForForgotPassword(email);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>("successfully", true, null, 200));
    }

   @PatchMapping("/reset-password")
    public ResponseEntity<ApiResponse<?>> resetPassword(@RequestBody @Valid ResetPasswordDto resetPasswordDto) {
        userService.resetPassword(resetPasswordDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("password changed successfully", true, null, 200));
   }

}














