package christmas.domain.payment.discount;

import static christmas.domain.payment.constants.Constants.PRESENT_YEAR;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public enum DayType {
    WEEKDAY,
    WEEKEND;

    public static boolean isWeekday(int day) {
        LocalDate orderDate = LocalDate.of(PRESENT_YEAR, Month.DECEMBER, day);
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();
        if (isSunday(day)) {
            return true;
        }
        return dayOfWeek.getValue() <= DayOfWeek.THURSDAY.getValue();
    }

    public static boolean isSunday(int day) {
        LocalDate orderDate = LocalDate.of(PRESENT_YEAR, Month.DECEMBER, day);
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();
        return dayOfWeek.getValue() == DayOfWeek.SUNDAY.getValue();
    }

    public static boolean isWeekend(int day) {
        LocalDate orderDate = LocalDate.of(PRESENT_YEAR, Month.DECEMBER, day);
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();
        return dayOfWeek.getValue() >= DayOfWeek.FRIDAY.getValue();
    }
}
