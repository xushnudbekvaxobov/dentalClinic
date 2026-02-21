package clinicManagement.entity;

import clinicManagement.util.enums.UserRole;
import clinicManagement.util.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    @Column( nullable = false,updatable = false)
    private LocalDate createdAt;
    @Column( nullable = false)
    private LocalDate updatedAt;
    private String verificationCode;
    private LocalDateTime verificationCodeGeneratedAt;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(nullable = false)
    private Boolean isAccountNonExpired;
    @Column(nullable = false)
    private Boolean isAccountNonLocked;
    @Column(nullable = false)
    private Boolean isCredentialsNonExpired;
    @Column(nullable = false)
    private Boolean isEnabled;



    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.name()));
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
