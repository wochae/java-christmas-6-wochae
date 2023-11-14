package christmas.domain.payment;

import static christmas.domain.payment.Constants.AMOUNT_ZERO;
import static christmas.domain.payment.Constants.BASE_DISCOUNT_AMOUNT;
import static christmas.domain.payment.Constants.BONUS_NO;
import static christmas.domain.payment.Constants.BONUS_PRICE;
import static christmas.domain.payment.Constants.BONUS_YES;
import static christmas.domain.payment.Constants.DAILY_DISCOUNT_INCREASE;
import static christmas.domain.payment.Constants.FIRST_DAY;
import static christmas.domain.payment.Constants.SPECIAL_DAY;
import static christmas.domain.payment.Constants.WEEKDAY_DESSERT_DISCOUNT;
import static christmas.domain.payment.Constants.WEEKEND_MAIN_DISCOUNT;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import christmas.domain.payment.discount.DayType;
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


    public static int applySpecialDiscount(int reservationDay) {
        int discount = 0;
        if (isSpecialDiscountDay(reservationDay)) {
            discount = DAILY_DISCOUNT_INCREASE * (reservationDay - FIRST_DAY);
            return discount + BASE_DISCOUNT_AMOUNT;
        }
        return discount;
    }

    private static boolean isSpecialDiscountDay(int day) {
        return FIRST_DAY <= day && day <= SPECIAL_DAY;
    }

    public static int applyStarDayDiscount(int reservationDay) {
        if (isStarDay(reservationDay)) {
            return BASE_DISCOUNT_AMOUNT;
        }
        return AMOUNT_ZERO;
    }

    private static boolean isStarDay(int reservationDay) {
        if (DayType.isSunday(reservationDay)) {
            return true;
        }
        return reservationDay == SPECIAL_DAY;
    }



    private static int applyGiftAmount(int totalAmount) {
        if (totalAmount >= BONUS_PRICE) {
            return BONUS_YES;
        }
        return BONUS_NO;
    }
}
