package clinicManagement.dto.responseDto;

import clinicManagement.util.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlotResponseDto {
    private LocalTime startTime;
    private LocalTime endTime;
    private AppointmentStatus status;
}
