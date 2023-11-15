package christmas.domain.payment;

import static christmas.domain.payment.constants.Constant.AMOUNT_ZERO;
import static christmas.domain.payment.constants.Constant.BASE_EVENT_PRICE;
import static christmas.domain.payment.constants.Constant.BONUS_DISCOUNT;
import static christmas.domain.payment.constants.Constant.BONUS_PRICE;
import static christmas.domain.payment.constants.PayMessage.AMOUNT_BENEFIT_PRICE;
import static christmas.domain.payment.constants.PayMessage.BADGE;
import static christmas.domain.payment.constants.PayMessage.EXPECTED_PRICE;
import static christmas.domain.payment.constants.PayMessage.PRICE_FORMAT;
import static christmas.domain.payment.constants.PayMessage.PRICE_MINUS_FORMAT;
import static christmas.domain.payment.constants.PayMessage.SPECIAL_DISCOUNT;
import static christmas.domain.payment.constants.PayMessage.BENEFIT_RECIPE;
import static christmas.domain.payment.constants.PayMessage.GIFT_EVENT;
import static christmas.domain.payment.constants.PayMessage.GIFT_EVENT_DISCOUNT;
import static christmas.domain.payment.constants.PayMessage.LOW_PRICE;
import static christmas.domain.payment.constants.PayMessage.NONE;
import static christmas.domain.payment.constants.PayMessage.ONE_BOTTLE;
import static christmas.domain.payment.constants.PayMessage.WEEKDAY_DISCOUNT;
import static christmas.domain.payment.constants.PayMessage.WEEKEND_DISCOUNT;
import static christmas.domain.payment.constants.PayMessage.X_MAX_DISCOUNT;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.payment.constants.PayMessage;
import christmas.domain.payment.discount.DayType;
import christmas.service.Payment;
import java.util.Map;

public record PaymentMessage(
        int reservationDay,
        Map<MenuItem, Integer> menuAndAmountMap,
        Payment payment) {

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        if (lowCustomer() || isEventCustomer()) {
            return lowPrice().toString();
        }
        message.append((GIFT_EVENT.getMessage()));
        message.append(grantCustomer());
        message.append(BENEFIT_RECIPE.getMessage());
        message.append(benefit());
        message.append(AMOUNT_BENEFIT_PRICE.getMessage());
        message.append(messageFactory(PRICE_MINUS_FORMAT, payment.getAllDiscount()));
        message.append(EXPECTED_PRICE.getMessage());
        message.append(messageFactory(PRICE_FORMAT, payment.getFinPrice()));
        message.append(badge());
        return message.toString();
    }

    private boolean isEventCustomer() {
        return payment.getRawTotal(menuAndAmountMap) < BASE_EVENT_PRICE;
    }
    private boolean lowCustomer() {
        return payment.getRawTotal(menuAndAmountMap) < BONUS_PRICE &&
                payment.getDayType(reservationDay) == DayType.WEEKDAY &&
                payment.getSpecialDayDiscount() == AMOUNT_ZERO;
    }
    private String badge() {
        StringBuilder message = new StringBuilder();
        message.append(BADGE.getMessage());
        message.append(payment.getBadge());
        return message.toString();
    }

    private String benefit() {
        StringBuilder message = new StringBuilder();
        message.append(messageFactory(X_MAX_DISCOUNT, payment.getSpecialDayDiscount()));
        message.append(dayType());
        message.append(messageFactory(SPECIAL_DISCOUNT, payment.getStarDayDiscount()));
        message.append(messageFactory(GIFT_EVENT_DISCOUNT, payment.getBigCustomerGift()));
        return message.toString();
    }

    private String messageFactory(PayMessage message, int price) {
        return String.format(message.getMessage(), price);
    }

    private String dayType() {
        if (payment.getDayType(reservationDay) == DayType.WEEKDAY) {
            return messageFactory(WEEKDAY_DISCOUNT, payment.getDiscountMenuOnDayType());
        }
        return messageFactory(WEEKEND_DISCOUNT, payment.getDiscountMenuOnDayType());
    }


    private String grantCustomer() {

        if (payment.getBigCustomerGift() == BONUS_DISCOUNT) {
            return ONE_BOTTLE.getMessage();
        }
        return NONE.getMessage();
    }

    private StringBuilder lowPrice() {
        StringBuilder message = new StringBuilder();
        message.append(messageFactory(LOW_PRICE, payment.getFinPrice()));
        return message;
    }
}
