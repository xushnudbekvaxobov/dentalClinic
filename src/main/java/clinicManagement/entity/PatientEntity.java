package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientEntity extends BaseEntity {
    private String allergies;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
