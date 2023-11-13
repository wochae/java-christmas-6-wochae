package christmas.domain.booking.dto;

import java.util.Map;

public record BookMessage(int reservationDay, Map<MenuItem, Integer> menuAndAmountMap) {
}
