package christmas.controller;

public class XmasMainController {
    public static void start() {
        int reservation = BookingController.requestBooking();
        MenuController.printReservationMenu(reservation);
    }
}
