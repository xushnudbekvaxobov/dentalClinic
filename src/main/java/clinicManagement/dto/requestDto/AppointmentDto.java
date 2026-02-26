package clinicManagement.dto.requestDto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;
}
