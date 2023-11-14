package christmas.service;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import christmas.domain.payment.discount.DayType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void getRawTotal() {
        // given
        int reservationDay = 26;
        Map<MenuItem, Integer> expected = new HashMap<>();
        Map<MenuItem, Integer> menuAndAmountMap = new HashMap<>();
        MenuItem MenuItem1 = new MenuItem(MenuType.MAIN, "티본스테이크", 55_000);
        MenuItem MenuItem2 = new MenuItem(MenuType.MAIN, "바비큐립", 54_000);
        MenuItem MenuItem3 = new MenuItem(MenuType.DESSERT, "초코케이크", 15_000);
        expected.put(MenuItem1, 1);
        expected.put(MenuItem2, 1);
        expected.put(MenuItem3, 2);
        menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2");
        Payment payment = new Payment(reservationDay, menuAndAmountMap);
        // when
        int expectedTotal = 139_000;
        int actualTotal = payment.getRawTotal(menuAndAmountMap);
        // then
        assertEquals(expectedTotal, actualTotal);
    }


    @Test
    void getDayType() {
        // given
        int reservationDay = 26;
        Payment payment = new Payment(reservationDay, null);
        // when
        DayType expected = DayType.WEEKDAY;
        DayType actual = payment.getDayType(reservationDay);
        // then
        assertEquals(expected, actual);
    }

    @Test
    void getDiscountMenuOnDayType() {
        // given
        int reservationDay1 = 26;
        int reservationDay2 = 29;
        Map<MenuItem, Integer> menuAndAmountMap = new HashMap<>();
        menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Payment payment1 = new Payment(reservationDay1, menuAndAmountMap);
        Payment payment2 = new Payment(reservationDay2, menuAndAmountMap);
        // when
        int expectedTotal1 = 4_046;
        int expectedTotal2 = 4_046;
        int actualTotal1 = payment1.getDiscountMenuOnDayType();
        int actualTotal2 = payment2.getDiscountMenuOnDayType();
        // then
        assertEquals(expectedTotal1, actualTotal1);
        assertEquals(expectedTotal2, actualTotal2);
    }

    @Test
    void getSpecialDay() {
        // given
        int reservationDay1 = 1;
        int reservationDay2 = 25;
        Map<MenuItem, Integer> menuAndAmountMap = new HashMap<>();
        menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Payment payment1 = new Payment(reservationDay1, menuAndAmountMap);
        Payment payment2 = new Payment(reservationDay2, menuAndAmountMap);
        // when
        int expectedTotal1 = 1000;
        int expectedTotal2 = 3400;
        int actualTotal1 = payment1.getSpecialDayDiscount();
        int actualTotal2 = payment2.getSpecialDayDiscount();
        // then
        assertEquals(expectedTotal1, actualTotal1);
        assertEquals(expectedTotal2, actualTotal2);
    }
}