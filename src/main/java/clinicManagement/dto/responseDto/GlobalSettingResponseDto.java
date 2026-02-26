package clinicManagement.dto.responseDto;

import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalSettingResponseDto {
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String reason;
    private Integer slotDuration;
}
