package clinicManagement.entity;

import clinicManagement.util.enums.DoctorStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;
    @Column(nullable = false)
    private String speciality;
    @Column(unique = true,nullable = false)
    private String licenseNumber;
    @Column(nullable = false)
    private Integer experienceYear;
    private DoctorStatus status;
}
