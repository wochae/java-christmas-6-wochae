package christmas.service;

import static christmas.domain.payment.constants.Constants.AMOUNT_ZERO;
import static christmas.domain.payment.constants.Constants.BONUS_DISCOUNT;
import static christmas.domain.payment.constants.Constants.BONUS_PRICE;

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

    public int getBigCustomerGift() {
        if (getRawTotal(menuAndAmountMap) >= BONUS_PRICE)
            return BONUS_DISCOUNT;
        return AMOUNT_ZERO;
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

    public int getAllDiscount() {
        int bigCustomerGift = getBigCustomerGift();
        int starDayDiscount = getStarDayDiscount();
        int specialDayDiscount = getSpecialDayDiscount();
        int discountMenuOnDayType = getDiscountMenuOnDayType();

        int totalDiscount = bigCustomerGift + starDayDiscount + specialDayDiscount + discountMenuOnDayType;
        return totalDiscount;
    }
    public int getFinPrice() {

        int totalAmount = getRawTotal(menuAndAmountMap);
        int starDayDiscount = getStarDayDiscount();
        int specialDayDiscount = getSpecialDayDiscount();
        int discountMenuOnDayType = getDiscountMenuOnDayType();

        int totalDiscount = starDayDiscount + specialDayDiscount + discountMenuOnDayType;
        int finalAmount = totalAmount - totalDiscount;
        return finalAmount;
    }
}
