package christmas.controller;

import christmas.domain.booking.dto.BookMessage;
import christmas.view.output.BookingWriterView;

public class XmasMainController {
    public static void start() {
        ReservationController reservationController = new ReservationController();
        BookMessage bookMessage = reservationController.getMessages();
        BookingWriterView.MenuOrder(bookMessage);
        PaymentController.pay(bookMessage);
    }
}
