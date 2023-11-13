package christmas.domain.booking;

import static christmas.exception.ErrorMessage.REQUEST_INVALID_MENU;

import christmas.exception.PlannerException;
import java.util.Map;

public class Booking {

    private final int reservationDay;
    private final Map<String, Integer> menuAndAmountMap;

    public Booking(int reservationDay, Map<String, Integer> menuAndAmountMap) {

        try {
            validationMenu(menuAndAmountMap);
        } catch (IllegalArgumentException exception) {
            PlannerException.of(REQUEST_INVALID_MENU, exception);
        }

        this.reservationDay = reservationDay;
        this.menuAndAmountMap = menuAndAmountMap;

    }

    public static Booking from(int reservationDay, Map<String, Integer> menuAndAmountMap) {
        return new Booking(reservationDay, menuAndAmountMap);
    }

    private void validationMenu(Map<String, Integer> menuAndAmountMap) {
        isExistMenu(menuAndAmountMap);
    }

    private void isExistMenu(Map<String, Integer> menuAndAmountMap) {

    }
}
