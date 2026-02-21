package clinicManagement.service.impl;

import clinicManagement.dto.requestDto.LoginDto;
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

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

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
    public ResponseEntity<ApiResponse<?>> register(UserDto userDto) {
        String email = userDto.getEmail();
        Optional<UserEntity> optional = userRepository.findByEmail(email);
        if (optional.isPresent()) {
            throw new AppBadException("user already registered");
        }
            String verificationCode = EmailServiceImpl.generateVerificationCode();
            emailService.sendVerificationCode(email, verificationCode);
            UserEntity userEntity = userMapper.toUserEntityForPatient(userDto, verificationCode);
            userRepository.save(userEntity);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("successfully registered", true, null, 201));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> login(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (!bCryptPasswordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            throw new NotAcceptableException("invalid password");
        }
        if(userEntity.getStatus() == UserStatus.NOT_ACTIVE){
            throw new NotAcceptableException("email not verified");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("successfully logged in", true,new JwtResponseDto(jwtTokenService.generateToken(userEntity)), 200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> verifyEmail(VerifyEmailDto verifyEmailDto) {
        String email = verifyEmailDto.getEmail();
        String verificationCode = verifyEmailDto.getVerificationCode();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (!userEntity.getVerificationCode().equals(verificationCode)) {
            throw new NotAcceptableException("invalid verification code");
        }
        if(LocalDateTime.now().isAfter(userEntity.getVerificationCodeGeneratedAt().plusMinutes(5))){
            throw new NotAcceptableException("verification code expired");
        }
        userEntity.setStatus(UserStatus.ACTIVE);
        userRepository.save(userEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("email successfully verified", true, null, 200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> sendVerificationCodeForForgotPassword(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (userEntity.getStatus() == UserStatus.NOT_ACTIVE) {
            throw new NotAcceptableException("email not verified");
        }
        String verificationCode = EmailServiceImpl.generateVerificationCode();
        emailService.sendVerificationCode(email, verificationCode);
        userEntity.setVerificationCode(verificationCode);
        userEntity.setVerificationCodeGeneratedAt(LocalDateTime.now());
        userRepository.save(userEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("verification code sent to your email please check your email", true, null, 200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> checkVerificationCodeForForgotPassword(VerifyEmailDto verifyEmailDto) {
        String email = verifyEmailDto.getEmail();
        String verificationCode = verifyEmailDto.getVerificationCode();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (userEntity.getStatus() == UserStatus.NOT_ACTIVE) {
            throw new NotAcceptableException("email NOT_ACTIVE");
        }
        if (!userEntity.getVerificationCode().equals(verificationCode)) {
            throw new AppBadException("invalid verification code");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("verification code is valid", true, null, 200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> changePassword(String email, String newPassword) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("user not found"));
        if (userEntity.getStatus() == UserStatus.NOT_ACTIVE) {
            throw new NotAcceptableException("user NOT_ACTIVE");
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(userEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("password successfully changed", true, null, 200));
    }

    @Override
    public ResponseEntity<ApiResponse<?>> findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new DataNotFoundException("user not found"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>("getting user by id",true,userEntity,200));
    }



}































