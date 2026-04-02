package clinicManagement.entity;

import clinicManagement.util.enums.DoctorStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorEntity extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;
    @OneToMany(mappedBy = "doctorEntity")
    private List<WorkingTimeEntity> workingTimeEntity;
    @Column(nullable = false)
    private String speciality;
    @Column(unique = true,nullable = false)
    private String licenseNumber;
    @Column(nullable = false)
    private Integer experienceYear;
    private DoctorStatus status;
}
