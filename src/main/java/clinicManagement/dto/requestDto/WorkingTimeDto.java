package clinicManagement.dto.requestDto;

import lombok.Getter;
import lombok.Setter;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
public class WorkingTimeDto {
    private DayOfWeek dayOfWeek;
    private LocalDate weekStart;
    private String startTime;
    private String endTime;
}
