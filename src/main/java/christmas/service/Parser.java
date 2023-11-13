package christmas.service;

import static christmas.domain.booking.constants.Constant.AMOUNT_INDEX;
import static christmas.domain.booking.constants.Constant.FIRST_DAY;
import static christmas.domain.booking.constants.Constant.LAST_DAY;
import static christmas.domain.booking.constants.Constant.MENU_AMOUNT_DELIMITER;
import static christmas.domain.booking.constants.Constant.MENU_INDEX;
import static christmas.domain.booking.constants.Constant.MENU_TYPE_DELIMITER;
import static christmas.domain.booking.constants.Constant.SEPARATE_TWO;
import static christmas.exception.ErrorMessage.DATE_OUT_OF_RANGE;
import static christmas.exception.ErrorMessage.REQUEST_INVALID_DATE;
import static christmas.exception.ErrorMessage.REQUEST_INVALID_MENU;

import christmas.exception.PlannerException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static int parseInt(String input) {
        try {
            validateOutOfRange(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw PlannerException.of(REQUEST_INVALID_DATE, exception);
        }
    }

    private static void validateOutOfRange(String input) {
        int day = Integer.parseInt(input);
        if (FIRST_DAY <= day && day <= LAST_DAY) {
            return;
        }
        throw PlannerException.from(DATE_OUT_OF_RANGE);
    }

    public static Map<String, Integer> splitMenuAndAmount(String input) {
        Map<String, Integer> menuAndAmountMap = new HashMap<>();
        try {
            validateWhiteSpace(input);
            validateOmittedArgument(input);
            String[] menuAndAmount = input.split(MENU_TYPE_DELIMITER);
            Arrays.stream(menuAndAmount)
                    .forEach(entry -> processMenuEntry(entry, menuAndAmountMap));
            validateEmpty(menuAndAmountMap);
        } catch (PlannerException exception) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
        if (menuAndAmountMap.isEmpty()) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
        return menuAndAmountMap;
    }

    private static void processMenuEntry(String entry, Map<String, Integer> menuAndAmountMap) {
        String[] parts = entry.split(MENU_AMOUNT_DELIMITER);
        if (parts.length == SEPARATE_TWO) {
            menuAndAmountMap.put(parts[MENU_INDEX].trim(), Integer.parseInt(parts[AMOUNT_INDEX].trim()));
        }
    }

    private static void validateEmpty(Map<String, Integer> menuAndAmountMap) {
        if (menuAndAmountMap.isEmpty()) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }

    private static void validateOmittedArgument(String input) {
        if (input.endsWith(MENU_AMOUNT_DELIMITER) || input.endsWith(MENU_TYPE_DELIMITER)) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }

    private static void validateWhiteSpace(String input) {
        if (input.contains(" ")) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }
}
