package christmas.controller;

import christmas.domain.booking.Booking;

public class XmasMainController {
    public static void start() {
        ReservationController reservationController = new ReservationController();
        Booking booking = reservationController.getBooking();

    }
}
