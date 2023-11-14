package christmas.service;

import static christmas.domain.payment.Constants.SPECIAL_DAY;

import christmas.domain.booking.Booking;
import christmas.domain.booking.dto.MenuItem;
import christmas.domain.payment.DiscountCalculator;
import christmas.domain.payment.discount.DayType;
import java.util.Map;

public class Payment {
    private final int reservationDay;
    private final Map<MenuItem, Integer> menuAndAmountMap;
    private final DayType dayType;

    public Payment(int reservationDay, Map<MenuItem, Integer> menuAndAmountMap) {
        this.reservationDay = reservationDay;
        this.menuAndAmountMap = menuAndAmountMap;
        this.dayType = getDayType(reservationDay);
    }

    public int getRawTotal(Map<MenuItem, Integer> menuAndAmountMap) {
        return Booking.getTotalAmount(menuAndAmountMap);
    }

    public DayType getDayType(int reservationDay) {
        if (DayType.isWeekday(reservationDay)) {
            return DayType.WEEKDAY;
        }
        if (DayType.isWeekend(reservationDay)) {
            return DayType.WEEKEND;
        }
        return dayType;
    }

    public int getStarDayDiscount() {
        return DiscountCalculator.applyStarDayDiscount(reservationDay);
    }

    public int getSpecialDayDiscount() {
        return DiscountCalculator.applySpecialDiscount(reservationDay);
    }

    public int getDiscountMenuOnDayType() {
        if (this.dayType == DayType.WEEKDAY) {
            return DiscountCalculator.applyWeekdayDiscount(menuAndAmountMap);
        }
        return DiscountCalculator.applyWeekendDiscount(menuAndAmountMap);
    }
}
