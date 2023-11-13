package christmas.domain.booking;

import christmas.domain.booking.dto.BookMessage;
import christmas.domain.booking.dto.MenuItem;
import java.util.Map;
import java.util.Optional;

public class Booking {

    private final int reservationDay;
    private final Map<MenuItem, Integer> menuAndAmountMap;

    public Booking(int reservationDay, Map<MenuItem, Integer> menuAndAmountMap) {
        this.reservationDay = reservationDay;
        this.menuAndAmountMap = menuAndAmountMap;

    }

    public static Booking from(int reservationDay, Map<MenuItem, Integer> menuAndAmountMap) {
        return new Booking(reservationDay, menuAndAmountMap);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "reservationDay=" + reservationDay +
                ", menuAndAmountMap=" + menuAndAmountMap.toString() +
                '}';
    }



    public BookMessage getMessages() {
        return new BookMessage(reservationDay, menuAndAmountMap);
    }
    private void validationMenu(Map<Optional<MenuItem>, Integer> menuAndAmountMap) {

    }
}
