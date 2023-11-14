package christmas.view.output;

import static christmas.domain.booking.constants.Message.AMOUNT_BEFORE_DISCOUNT;
import static christmas.domain.booking.constants.Message.ORDER;
import static christmas.domain.booking.constants.Message.ORDER_DETAIL;
import static christmas.domain.booking.constants.Message.RECEPTION;

import christmas.domain.booking.dto.BookMessage;
import java.text.DecimalFormat;

public class BookingWriterView extends OutputView {
    private BookingWriterView() {
    }

    public static void MenuOrder(BookMessage bookMessage) {
        String message = String.format(RECEPTION.getMessage(), bookMessage.reservationDay());
        long[] amount = {0};
        bookMessage.menuAndAmountMap().forEach((key, value) -> {
            amount[0] += (long) key.price() * value;
        });
        println(message);
        println(ORDER.getMessage());
        bookMessage.menuAndAmountMap().forEach((key, value) -> {
            println(String.format(ORDER_DETAIL.getMessage(), key.name(), value));

        });
        totalRawPrice(amount);
    }

    private static void totalRawPrice(long[] amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        String formatted = df.format(amount[0]);
        println(String.format(AMOUNT_BEFORE_DISCOUNT.getMessage(), formatted));
    }
}
