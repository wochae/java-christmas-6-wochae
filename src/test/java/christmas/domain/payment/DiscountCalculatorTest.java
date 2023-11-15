package christmas.domain.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.booking.dto.MenuItem;
import christmas.service.Parser;
import christmas.service.Payment;
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

    @Test
    void generatePaymentMessage() {
        // given
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2";
        Map<MenuItem, Integer> menuAndAmountMap;
        menuAndAmountMap = Parser.splitMenuAndAmount(input);
        int reservation = 26;
        Payment payment = new Payment(reservation, menuAndAmountMap);

        // when
        PaymentMessage paymentMessage = DiscountCalculator.generatePaymentMessage(reservation, menuAndAmountMap);

        // then
        assertEquals(paymentMessage.menuAndAmountMap(), menuAndAmountMap);
        assertEquals(paymentMessage.payment().getRawTotal(paymentMessage.menuAndAmountMap()), payment.getRawTotal(menuAndAmountMap));
    }
}