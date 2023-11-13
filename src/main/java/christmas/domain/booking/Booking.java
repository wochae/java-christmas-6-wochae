package christmas.domain.booking;

import static christmas.domain.booking.constants.Constant.AMOUNT_MAX;
import static christmas.exception.ErrorMessage.AMOUNT_OUT_OF_RANGE;

import christmas.exception.PlannerException;
import java.util.List;
import java.util.Map;

public class Booking {

    private final int reservationDay;
    private final Map<String, Integer> menuAndAmountMap;

    public Booking(int reservationDay, Map<String, Integer> menuAndAmountMap) {
        validationMenu(menuAndAmountMap);
        this.reservationDay = reservationDay;
        this.menuAndAmountMap = menuAndAmountMap;

    }

    public static Booking from(int reservationDay, Map<String, Integer> menuAndAmountMap) {
        return new Booking(reservationDay, menuAndAmountMap);
    }

    private void validationMenu(Map<String, Integer> menuAndAmountMap) {
        validateNumbersRange(List.copyOf(menuAndAmountMap.values()));
    }

    private void validateNumbersRange(List<Integer> numbers) {
        if (eachMaxAmount(numbers)) {
            throw PlannerException.from(AMOUNT_OUT_OF_RANGE);
        }
    }

    private boolean eachMaxAmount(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(number -> number > AMOUNT_MAX);
    }
}
