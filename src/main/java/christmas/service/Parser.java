package christmas.service;

import static christmas.exception.ErrorMessage.DATE_OUT_OF_RANGE;
import static christmas.exception.ErrorMessage.REQUEST_NOT_INTEGER;

import christmas.exception.DateException;

public class Parser {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;

    public static int parseInt(String input) {
        try {
            validateOutOfRange(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw DateException.of(REQUEST_NOT_INTEGER, exception);
        }
    }

    private static void validateOutOfRange(String input) {
        int day = Integer.parseInt(input);
        if (FIRST_DAY <= day && day <= LAST_DAY) {
            return;
        }
        throw DateException.from(DATE_OUT_OF_RANGE);
    }
}
