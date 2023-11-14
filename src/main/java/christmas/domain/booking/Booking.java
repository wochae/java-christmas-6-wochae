package christmas.domain.booking;

import christmas.domain.booking.dto.MenuItem;
import java.util.Map;

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

    public static int getTotalAmount(Map<MenuItem, Integer> processedMenuAndAmountMap) {
        int totalAmount = 0;
        for (MenuItem menuItem : processedMenuAndAmountMap.keySet()) {
            totalAmount += menuItem.price() * processedMenuAndAmountMap.get(menuItem);
        }
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "reservationDay=" + reservationDay +
                ", menuAndAmountMap=" + menuAndAmountMap.toString() +
                '}';
    }
}
