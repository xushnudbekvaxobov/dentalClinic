package clinicManagement.service.util;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class CustomTimeHelper {
    public static LocalDate thisWeekStart(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }
    public static LocalDate nextWeekStart(LocalDate date) {
        return date.with(DayOfWeek.MONDAY).plusWeeks(1);
    }

}
