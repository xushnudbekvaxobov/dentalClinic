package clinicManagement.entity;

import clinicManagement.util.enums.VisitStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "visits")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id",nullable = false)
    private PatientEntity patientEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id",nullable = false)
    private DoctorEntity doctorEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private AppointmentEntity appointmentEntity;
    private boolean isWalkIn;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate visitDate;
    private LocalTime startedAt;
    private LocalTime endedAt;
    @Column(nullable = false)
    private String queueNumber;
    @Column(length = 1000)
    private String notes;
    @Column(nullable = false)
    private VisitStatus status;
}
