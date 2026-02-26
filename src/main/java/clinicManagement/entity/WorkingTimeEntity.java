package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "working_time")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch  = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctorEntity;
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @Column(nullable = false)
    private LocalDate workingDate;
    @Column(nullable = false)
    private LocalTime startTime;
    @Column(nullable = false)
    private LocalTime endTime;
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String reason;
    @Column(nullable = false)
    private Boolean isExpired;
}
