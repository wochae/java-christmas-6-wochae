package christmas.domain.payment;

import static christmas.domain.payment.Constants.WEEKDAY_DESSERT_DISCOUNT;
import static christmas.domain.payment.Constants.WEEKEND_MAIN_DISCOUNT;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import java.util.Map;

public class DiscountCalculator {
    public static int applyWeekdayDiscount(Map<MenuItem, Integer> menuAndAmountMap) {
        int weekdayDiscount = 0;

        for (Map.Entry<MenuItem, Integer> entry : menuAndAmountMap.entrySet()) {
            MenuItem menuItem = entry.getKey();
            int quantity = entry.getValue();

            if (menuItem.type() == MenuType.DESSERT) {
                weekdayDiscount += quantity * WEEKDAY_DESSERT_DISCOUNT;
            }
        }

        return weekdayDiscount;
    }

    public static int applyWeekendDiscount(Map<MenuItem, Integer> menuAndAmountMap) {
        int weekendDiscount = 0;

        for (Map.Entry<MenuItem, Integer> entry : menuAndAmountMap.entrySet()) {
            MenuItem menuItem = entry.getKey();
            if (menuItem.type() == MenuType.MAIN) {
                weekendDiscount += entry.getValue() * WEEKEND_MAIN_DISCOUNT;
            }
        }
        return weekendDiscount;
    }
}
