package christmas.domain.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.booking.dto.MenuItem;
import christmas.service.Parser;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {

    @Test
    void applyWeekdayDiscount() {
        // given
        Map<MenuItem, Integer> menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2");
        // when
        int price = DiscountCalculator.applyWeekdayDiscount(menuAndAmountMap);
        // then
        assertEquals(price, 4046);
    }

    @Test
    void applyWeekendDiscount() {
    }

    @Test
    void applySpecialDiscount() {
    }

    @Test
    void applyStarDayDiscount() {
    }
}