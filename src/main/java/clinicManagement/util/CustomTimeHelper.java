package clinicManagement.util;

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
    public static LocalDate thisMonthStart(LocalDate date) {
        return date.withDayOfMonth(1);
    }
    public static LocalDate endOfMonth(LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public static LocalDate nextMonthStart(LocalDate date) {
        return date.withDayOfMonth(1).plusMonths(1);
    }


}
