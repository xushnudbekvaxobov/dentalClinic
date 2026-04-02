package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "global_settings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalSettingsEntity extends BaseEntity {
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String reason;
    private Integer slotDuration;
}
