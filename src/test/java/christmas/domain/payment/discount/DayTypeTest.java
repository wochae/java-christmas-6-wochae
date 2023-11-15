package christmas.domain.payment.discount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DayTypeTest {


    @Test
    void isWeekday() {
        // 요구사항에 일요일은 주말로 간주하지 않는다.
        assertTrue(DayType.isWeekday(3));
    }

    @Test
    void isSunday() {
        // 하지만 일요일인 것은 불변의 사실.
        assertTrue(DayType.isSunday(3));
    }

    @Test
    void isWeekend() {
        assertTrue(DayType.isWeekend(2));
    }
}