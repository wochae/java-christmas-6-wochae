package christmas.controller;

import christmas.domain.booking.dto.BookMessage;
import christmas.domain.booking.dto.MenuItem;
import christmas.domain.payment.DiscountCalculator;
import christmas.domain.payment.PaymentMessage;
import christmas.view.output.PaymentWriterView;
import java.util.Map;

public class PaymentController {
    public static void pay(BookMessage bookMessage) {
        int day = bookMessage.reservationDay();
        Map<MenuItem, Integer> menus = bookMessage.menuAndAmountMap();
        PaymentMessage PaymentMessage = DiscountCalculator.generatePaymentMessage(day, menus);
        PaymentWriterView.println(PaymentMessage.toString());
    }
}
