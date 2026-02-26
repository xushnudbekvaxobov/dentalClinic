package clinicManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "global_setting")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalSettingsEntity {
    @Id
    private Long id;
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String reason;
    private Integer slotDuration;
}
