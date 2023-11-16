package christmas.domain.booking;

import static christmas.domain.payment.constants.Constant.AMOUNT_ZERO;

import christmas.domain.booking.dto.MenuItem;
import java.util.Map;

public class Booking {

    public Booking(int reservationDay, Map<MenuItem, Integer> menuAndAmountMap) {
    }

    public static Booking from(int reservationDay, Map<MenuItem, Integer> menuAndAmountMap) {
        return new Booking(reservationDay, menuAndAmountMap);
    }

    public static int getTotalAmount(Map<MenuItem, Integer> processedMenuAndAmountMap) {
        int totalAmount = AMOUNT_ZERO;
        for (MenuItem menuItem : processedMenuAndAmountMap.keySet()) {
            totalAmount += menuItem.price() * processedMenuAndAmountMap.get(menuItem);
        }
        return totalAmount;
    }
}
