package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.LoginDto;
import clinicManagement.dto.requestDto.ResetPasswordDto;
import clinicManagement.dto.requestDto.UserDto;
import clinicManagement.dto.requestDto.VerifyEmailDto;
import clinicManagement.dto.responseDto.ApiResponse;
import clinicManagement.dto.responseDto.JwtResponseDto;
import clinicManagement.entity.UserEntity;
import clinicManagement.exception.AppBadException;
import clinicManagement.exception.DataNotFoundException;
import clinicManagement.exception.NotAcceptableException;
import clinicManagement.jwt.JwtTokenService;
import clinicManagement.mapper.UserMapper;
import clinicManagement.repository.UserRepository;
import clinicManagement.service.EmailService;
import clinicManagement.service.UserService;
import clinicManagement.util.enums.UserStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static clinicManagement.service.impl.EmailServiceImpl.generateVerificationCode;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenService jwtTokenService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           @Lazy BCryptPasswordEncoder bCryptPasswordEncoder, @Lazy JwtTokenService jwtTokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        }
        return optional.get();
    }

    @Override
    public void register(UserDto userDto) {
        String email = userDto.getEmail();
        Optional<UserEntity> optional = userRepository.findByEmail(email);
        if (optional.isPresent()) {
            throw new NotAcceptableException("User already registered with email " +  email);
        }
            String verificationCode = EmailServiceImpl.generateVerificationCode();
            emailService.sendVerificationCode(email, verificationCode);
            UserEntity userEntity = userMapper.toUserEntityForPatient(userDto, verificationCode);
            userRepository.save(userEntity);
    }

    @Override
    public JwtResponseDto login(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new DataNotFoundException("User not found with email " + loginDto.getEmail()));
        if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new NotAcceptableException("Your password is not correct, please create right password!");
        }
        if(userEntity.getStatus() == UserStatus.NOT_ACTIVE){
            throw new NotAcceptableException("User status is not active!");
        }
        return new JwtResponseDto(jwtTokenService.generateToken(userEntity));
    }

    @Override
    public void verifyEmail(VerifyEmailDto verifyEmailDto) {
        String email = verifyEmailDto.getEmail();
        String verificationCode = verifyEmailDto.getVerificationCode();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found with email " + email));
        if (!userEntity.getVerificationCode().equals(verificationCode)) {
            throw new NotAcceptableException("Verification code is not correct, please create right verification code!");
        }
        if (LocalDateTime.now().isAfter(userEntity.getVerificationCodeGeneratedAt().plusMinutes(5))) {
            throw new NotAcceptableException("Verification code expired, please create new verification code!");
        }
        userEntity.setStatus(UserStatus.ACTIVE);
        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void sendVerificationCodeForForgotPassword(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("User not found with email " + email));
        if (userEntity.getStatus() == UserStatus.NOT_ACTIVE) {
            throw new NotAcceptableException("User status is not active!");
        }
        String verificationCode = EmailServiceImpl.generateVerificationCode();
        emailService.sendVerificationCode(email, verificationCode);
        userEntity.setVerificationCode(verificationCode);
        userEntity.setVerificationCodeGeneratedAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public void resetPassword(ResetPasswordDto  resetPasswordDto) {
        UserEntity userEntity = userRepository.findByEmail(resetPasswordDto.getEmail()).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (userEntity.getStatus() == UserStatus.NOT_ACTIVE) {
            throw new NotAcceptableException("email not verified");
        }
        if (!resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmPassword())) {
            throw new NotAcceptableException("passwords do not match");
        }
        if (userEntity.getVerificationCode() == null ||!userEntity.getVerificationCode().equals(resetPasswordDto.getVerificationCode())) {
            throw new NotAcceptableException("invalid verification code");
        }
        if (LocalDateTime.now().isAfter(userEntity.getVerificationCodeGeneratedAt().plusMinutes(3))) {
            throw new NotAcceptableException("verification code expired");
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(resetPasswordDto.getNewPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public ResponseEntity<ApiResponse<?>> findById(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("user not found"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("getting user by id",true,userEntity,200));
    }



}































