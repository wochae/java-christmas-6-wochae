package christmas.controller;

import christmas.domain.booking.Booking;
import christmas.domain.booking.dto.MenuItem;
import christmas.view.output.OutputView;
import java.util.Map;

public class ReservationController {

    private final int reservation;
    private Map<String, Integer> menuAndAmountMap;
    private Map<MenuItem, Integer> processedMenuAndAmountMap;

    public ReservationController() {
        this.reservation = BookingController.requestBooking();
        getMenuWithRetry();
    }

    private void getMenuWithRetry() {
        try {
            processedMenuAndAmountMap = MenuController.requestMenu();
            Booking.from(reservation, processedMenuAndAmountMap);
        } catch (IllegalArgumentException exception) {
            OutputView.println(exception.getMessage());
            getMenuWithRetry();
        }
    }

    public Booking getBooking() {
        return Booking.from(reservation, processedMenuAndAmountMap);
    }

}
