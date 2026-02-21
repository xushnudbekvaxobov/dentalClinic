package clinicManagement.mapper;

import clinicManagement.dto.requestDto.UserDto;
import clinicManagement.dto.requestDto.UserEmployeeDto;
import clinicManagement.entity.UserEntity;
import clinicManagement.util.enums.UserRole;
import clinicManagement.util.enums.UserStatus;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class UserMapper {
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserMapper(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserEntity toUserEntityForPatient(UserDto userDto, String verificationCode) {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userEntity.setRole(UserRole.ROLE_PATIENT);
        return getUserEntity(verificationCode, userEntity);
    }
    public UserEntity toUserEntityForEmployee(UserEmployeeDto userEmployeeDto, String verificationCode) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEmployeeDto.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEmployeeDto.getPassword()));
        userEntity.setRole(userEmployeeDto.getRole());
        return getUserEntity(verificationCode, userEntity);
    }

    @NonNull
    private UserEntity getUserEntity(String verificationCode, UserEntity userEntity) {
        userEntity.setCreatedAt(LocalDate.now());
        userEntity.setUpdatedAt(LocalDate.now());
        userEntity.setVerificationCode(verificationCode);
        userEntity.setVerificationCodeGeneratedAt(LocalDateTime.now());
        userEntity.setStatus(UserStatus.NOT_ACTIVE);
        userEntity.setIsAccountNonExpired(true);
        userEntity.setIsAccountNonLocked(true);
        userEntity.setIsCredentialsNonExpired(true);
        userEntity.setIsEnabled(true);
        return userEntity;
    }

}
