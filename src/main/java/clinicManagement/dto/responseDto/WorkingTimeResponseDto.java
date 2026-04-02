package clinicManagement.dto.responseDto;



import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingTimeResponseDto{
    private UUID doctorId;
    private DayOfWeek dayOfWeek;
    private LocalDate workingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String reason;
    private Boolean isExpired;
}
