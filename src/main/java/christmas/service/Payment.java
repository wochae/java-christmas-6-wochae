package christmas.service;

import static christmas.domain.payment.constants.Constant.AMOUNT_ZERO;
import static christmas.domain.payment.constants.Constant.BADGE_MINIMUM_PRICE;
import static christmas.domain.payment.constants.Constant.BONUS_DISCOUNT;
import static christmas.domain.payment.constants.Constant.BONUS_PRICE;
import static christmas.domain.payment.constants.PayMessage.NONE;
import static christmas.domain.payment.constants.PayMessage.SANTA;
import static christmas.domain.payment.constants.PayMessage.STAR;
import static christmas.domain.payment.constants.PayMessage.TREE;

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
        if (getRawTotal(menuAndAmountMap) >= BONUS_PRICE) {
            return BONUS_DISCOUNT;
        }
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
        return bigCustomerGift + starDayDiscount + specialDayDiscount + discountMenuOnDayType;
    }

    public String getBadge() {
        int price = BADGE_MINIMUM_PRICE;
        if (getAllDiscount() < price) {
            return NONE.getMessage();
        }
        price += price;
        if (getAllDiscount() < price) {
            return STAR.getMessage();
        }
        price += price;
        if (getAllDiscount() < price) {
            return TREE.getMessage();
        }
        return SANTA.getMessage();
    }

    public int getFinDiscount() {
        int starDayDiscount = getStarDayDiscount();
        int specialDayDiscount = getSpecialDayDiscount();
        int discountMenuOnDayType = getDiscountMenuOnDayType();
        return starDayDiscount + specialDayDiscount + discountMenuOnDayType;
    }

    public int getFinPrice() {
        int totalAmount = getRawTotal(menuAndAmountMap);
        return totalAmount - getFinDiscount();
    }

    public int getLowFinPrice() {
        return getRawTotal(menuAndAmountMap);
    }
}
