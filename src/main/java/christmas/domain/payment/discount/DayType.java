package christmas.domain.payment.discount;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public enum DayType {
    WEEKDAY,
    WEEKEND;

    public static boolean isWeekday(int day) {
        // Implement logic to check if the given day is a weekday (Sunday to Thursday)
        // For example, you can use the Java Calendar class
        // LocalDate를 사용하여 해당 날짜를 만듭니다.
        LocalDate orderDate = LocalDate.of(2023, Month.DECEMBER, day);

        // 해당 날짜의 요일을 확인합니다.
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();

        // 요일이 일요일(7)부터 목요일(4)까지인지 확인하여 주말 여부를 반환합니다.
        return dayOfWeek.getValue() <= DayOfWeek.THURSDAY.getValue();
    }


    public static boolean isWeekend(int day) {
        // Implement logic to check if the given day is a weekend (Friday or Saturday)
        // For example, you can use the Java Calendar class
        // LocalDate를 사용하여 해당 날짜를 만듭니다.
        LocalDate orderDate = LocalDate.of(2023, Month.DECEMBER, day);

        // 해당 날짜의 요일을 확인합니다.
        DayOfWeek dayOfWeek = orderDate.getDayOfWeek();

        // 요일이 금요일(5) 또는 토요일(6)인지 확인하여 주말 여부를 반환합니다.
        return dayOfWeek.getValue() >= DayOfWeek.FRIDAY.getValue();
    }
}
