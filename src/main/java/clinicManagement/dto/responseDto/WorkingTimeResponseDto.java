package clinicManagement.dto.responseDto;



import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingTimeResponseDto{
    private Long doctorId;
    private DayOfWeek dayOfWeek;
    private LocalDate workingDate;
    private String startTime;
    private String endTime;
    private Boolean isExpired;
}
