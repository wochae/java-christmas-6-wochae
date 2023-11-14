package christmas.domain.payment;

import static christmas.domain.payment.constants.Constants.AMOUNT_ZERO;
import static christmas.domain.payment.constants.Constants.BASE_DISCOUNT_AMOUNT;
import static christmas.domain.payment.constants.Constants.DAILY_DISCOUNT_INCREASE;
import static christmas.domain.payment.constants.Constants.FIRST_DAY;
import static christmas.domain.payment.constants.Constants.SPECIAL_DAY;
import static christmas.domain.payment.constants.Constants.WEEKDAY_DESSERT_DISCOUNT;
import static christmas.domain.payment.constants.Constants.WEEKEND_MAIN_DISCOUNT;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import christmas.domain.payment.discount.DayType;
import christmas.service.Payment;
import java.util.Map;

public class DiscountCalculator {
    public static int applyWeekdayDiscount(Map<MenuItem, Integer> menuAndAmountMap) {
        int weekdayDiscount = AMOUNT_ZERO;

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
        int weekendDiscount = AMOUNT_ZERO;

        for (Map.Entry<MenuItem, Integer> entry : menuAndAmountMap.entrySet()) {
            MenuItem menuItem = entry.getKey();
            if (menuItem.type() == MenuType.MAIN) {
                weekendDiscount += entry.getValue() * WEEKEND_MAIN_DISCOUNT;
            }
        }
        return weekendDiscount;
    }


    public static int applySpecialDiscount(int reservationDay) {
        int discount = AMOUNT_ZERO;
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

    public static PaymentMessage generatePaymentMessage(int day, Map<MenuItem, Integer> menus) {
        Payment payment = new Payment(day, menus);
        return new PaymentMessage(
                day,
                menus,
                payment
        );
    }
}
