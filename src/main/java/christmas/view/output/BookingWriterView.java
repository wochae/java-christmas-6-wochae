package christmas.view.output;

import static christmas.domain.booking.constants.Message.ORDER;
import static christmas.domain.booking.constants.Message.ORDER_DETAIL;
import static christmas.domain.booking.constants.Message.RECEPTION;

import christmas.domain.booking.dto.BookMessage;

public class BookingWriterView extends OutputView {
    private BookingWriterView() {
    }

    public static void MenuOrder(BookMessage bookMessage) {
        String message = String.format(RECEPTION.getMessage(), bookMessage.reservationDay());
        println(message);
        println(ORDER.getMessage());
        bookMessage.menuAndAmountMap().forEach((key, value) -> {
            println(String.format(ORDER_DETAIL.getMessage(), key.name(), value));
        });
    }
}
