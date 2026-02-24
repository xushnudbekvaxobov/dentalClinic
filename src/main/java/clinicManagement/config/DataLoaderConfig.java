package clinicManagement.config;

import clinicManagement.entity.UserEntity;
import clinicManagement.repository.UserRepository;
import clinicManagement.util.enums.UserRole;
import clinicManagement.util.enums.UserStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Configuration
public class DataLoaderConfig implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;
   private final UserRepository userRepository;
   private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public DataLoaderConfig(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if(sqlInitMode.equals("always")) {
            UserEntity admin = UserEntity.builder()
                    .email("admin@gmail.com")
                    .password(bCryptPasswordEncoder.encode("admin123"))
                    .role(UserRole.ROLE_ADMIN)
                    .createdAt(LocalDate.now())
                    .updatedAt(LocalDate.now())
                    .verificationCode(null)
                    .verificationCodeGeneratedAt(null)
                    .status(UserStatus.ACTIVE)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();
            userRepository.save(admin);
        }
    }
}
