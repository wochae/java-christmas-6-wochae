package christmas.domain.payment.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public enum DayType {
    WEEKDAY,
    WEEKEND;

    public static boolean isWeekday(int day) {
        LocalDate orderDate = LocalDate.of(2023, Month.DECEMBER, day);
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();
        return dayOfWeek.getValue() <= DayOfWeek.THURSDAY.getValue();
    }

    public static boolean isSunday(int day) {
        LocalDate orderDate = LocalDate.of(2023, Month.DECEMBER, day);
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();
        return dayOfWeek.getValue() == DayOfWeek.SUNDAY.getValue();
    }

    public static boolean isWeekend(int day) {
        LocalDate orderDate = LocalDate.of(2023, Month.DECEMBER, day);
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();
        return dayOfWeek.getValue() >= DayOfWeek.FRIDAY.getValue();
    }
}
