package clinicManagement.entity;

import clinicManagement.util.enums.EmployeeStatus;
import clinicManagement.util.enums.EmployeeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity extends BaseProfileEntity{
    @Column(nullable = false)
    private String speciality;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private UserEntity userEntity;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;
}