package christmas.service;

import static christmas.domain.payment.Constants.AMOUNT_ZERO;
import static christmas.domain.payment.Constants.BONUS_PRICE;
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
    void getBigCustomerGift() {
        // given
        int reservationDay1 = 26;
        Map<MenuItem, Integer> menuAndAmountMap1 = new HashMap<>();
        menuAndAmountMap1 = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Payment payment1 = new Payment(reservationDay1, menuAndAmountMap1);
        int reservationDay2 = 26;
        Map<MenuItem, Integer> menuAndAmountMap2 = new HashMap<>();
        menuAndAmountMap2 = Parser.splitMenuAndAmount("초코케이크-2,제로콜라-1");
        Payment payment2 = new Payment(reservationDay2, menuAndAmountMap2);
        // when
        int expectedTotal1 = BONUS_PRICE;
        int expectedTotal2 = AMOUNT_ZERO;
        int actualTotal1 = payment1.getBigCustomerGift();
        int actualTotal2 = payment2.getBigCustomerGift();
        // then
        assertEquals(expectedTotal1, actualTotal1);
        assertEquals(expectedTotal2, actualTotal2);
    }

    @Test
    void getDayType() {
        // given
        int reservationDay1 = 22;
        int reservationDay2 = 23;
        int reservationDay3 = 24;
        Payment payment1 = new Payment(reservationDay1, null);
        Payment payment2 = new Payment(reservationDay2, null);
        Payment payment3 = new Payment(reservationDay3, null);
        // when
        DayType expected1 = DayType.WEEKEND;
        DayType expected2 = DayType.WEEKEND;
        DayType expected3 = DayType.WEEKDAY;
        DayType actual1 = payment1.getDayType(reservationDay1);
        DayType actual2 = payment2.getDayType(reservationDay2);
        DayType actual3 = payment3.getDayType(reservationDay3);
        // then
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
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

    @Test
    void getStarDay() {
        // given
        int reservationDay1 = 1;
        int reservationDay2 = 24;
        int reservationDay3 = 25;
        Map<MenuItem, Integer> menuAndAmountMap = new HashMap<>();
        menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Payment payment1 = new Payment(reservationDay1, menuAndAmountMap);
        Payment payment2 = new Payment(reservationDay2, menuAndAmountMap);
        Payment payment3 = new Payment(reservationDay3, menuAndAmountMap);
        // when
        int expectedTotal1 = 0;
        int expectedTotal2 = 1000;
        int expectedTotal3 = 1000;
        int actualTotal1 = payment1.getStarDayDiscount();
        int actualTotal2 = payment2.getStarDayDiscount();
        int actualTotal3 = payment3.getStarDayDiscount();
        // then
        assertEquals(expectedTotal1, actualTotal1);
        assertEquals(expectedTotal2, actualTotal2);
        assertEquals(expectedTotal3, actualTotal3);
    }
}