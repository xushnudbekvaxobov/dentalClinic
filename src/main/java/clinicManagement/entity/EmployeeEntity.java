package clinicManagement.entity;

import clinicManagement.util.enums.EmployeeStatus;
import clinicManagement.util.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    @Column(nullable = false)
    private LocalDate updatedAt;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
}