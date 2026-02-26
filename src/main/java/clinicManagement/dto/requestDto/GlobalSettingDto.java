package clinicManagement.dto.requestDto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalSettingDto {
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String reason;
    private Integer slotDuration;
}
