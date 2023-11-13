package christmas.controller;

import christmas.domain.booking.Booking;
import java.util.Map;

public class ReservationController {

    private final int reservation;
    private final Map<String, Integer> menuAndAmountMap;

    public ReservationController() {
        this.reservation = BookingController.requestBooking();
        menuAndAmountMap = MenuController.requestMenu();
    }

    public Booking getBooking() {
        return Booking.from(reservation, menuAndAmountMap);
    }

}
