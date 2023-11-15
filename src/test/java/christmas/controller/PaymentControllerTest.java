package christmas.controller;

import static org.junit.jupiter.api.Assertions.*;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import christmas.service.Parser;
import christmas.service.Payment;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PaymentControllerTest {

    @Test
    void getRawTotal() {
        // given
        int reservationDay = 26;
        Map<MenuItem, Integer> expected = new HashMap<>();
        Map<MenuItem, Integer> menuAndAmountMap = new HashMap<>();
        MenuItem MenuItem1 = new MenuItem(MenuType.MAIN, "티본스테이크", 55_000);
        MenuItem MenuItem2 = new MenuItem(MenuType.MAIN, "바비큐립", 54_000);
        expected.put(MenuItem1, 1);
        expected.put(MenuItem2, 1);
        menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1");
        Payment payment = new Payment(reservationDay, menuAndAmountMap);
        // when
        int expectedTotal = 109_000;
        int actualTotal = payment.getRawTotal(menuAndAmountMap);
        // then
        assertEquals(expectedTotal, actualTotal);
    }
}