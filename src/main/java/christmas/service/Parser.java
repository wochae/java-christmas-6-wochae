package christmas.service;

import static christmas.domain.booking.MenuSearch.findMenuItem;
import static christmas.domain.booking.constants.Constant.AMOUNT_INDEX;
import static christmas.domain.booking.constants.Constant.AMOUNT_MAX;
import static christmas.domain.booking.constants.Constant.FIRST_DAY;
import static christmas.domain.booking.constants.Constant.LAST_DAY;
import static christmas.domain.booking.constants.Constant.MENU_AMOUNT_DELIMITER;
import static christmas.domain.booking.constants.Constant.MENU_INDEX;
import static christmas.domain.booking.constants.Constant.MENU_TYPE_DELIMITER;
import static christmas.domain.booking.constants.Constant.SEPARATE_TWO;
import static christmas.exception.ErrorMessage.AMOUNT_OUT_OF_RANGE;
import static christmas.exception.ErrorMessage.DATE_OUT_OF_RANGE;
import static christmas.exception.ErrorMessage.REQUEST_INVALID_DATE;
import static christmas.exception.ErrorMessage.REQUEST_INVALID_MENU;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import christmas.exception.PlannerException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public static Map<Optional<MenuItem>, Integer> splitMenuAndAmount(String input) {
        Map<Optional<MenuItem>, Integer> menuAndAmountMap = new HashMap<>();
        try {
            validateOmittedArgument(input);
            validateDuplicateMenu(input);
            validateFood(input);
            String[] menuAndAmount = input.split(MENU_TYPE_DELIMITER);
            Arrays.stream(menuAndAmount).forEach(entry -> processMenuEntry(entry, menuAndAmountMap));
            validateEmpty(menuAndAmountMap);
            validateAmount(sumAmount(menuAndAmountMap));
        } catch (PlannerException exception) {
            throw PlannerException.of(REQUEST_INVALID_MENU, exception);
        }
        return menuAndAmountMap;
    }

    private static int sumAmount(Map<Optional<MenuItem>, Integer> menuAndAmountMap) {
        return menuAndAmountMap.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void validateFood(String input) {
        try {
            onlyBeverage(input);
            certainFoods(input);
        } catch (PlannerException exception) {
            throw PlannerException.of(REQUEST_INVALID_MENU, exception);
        }
    }

    private static void certainFoods(String input) {
        String[] menuAndAmount = input.split(MENU_TYPE_DELIMITER);
        boolean allCertains = Arrays.stream(menuAndAmount)
                .map(entry -> entry.split(MENU_AMOUNT_DELIMITER)[MENU_INDEX])
                .allMatch(Parser::isCertainFood);
        if (!allCertains) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }

    private static boolean isCertainFood(String menu) {
        return findMenuItem(menu)
                .map(menuItem -> Arrays.asList(MenuType.values()).contains(menuItem.type()))
                .orElse(false);
    }

    private static void onlyBeverage(String input) {
        String[] menuAndAmount = input.split(MENU_TYPE_DELIMITER);
        boolean allMatch = Arrays.stream(menuAndAmount)
                .map(entry -> entry.split(MENU_AMOUNT_DELIMITER)[MENU_INDEX])
                .allMatch(menu -> findMenuItem(menu)
                        .map(menuItem -> menuItem.type() == MenuType.BEVERAGE)
                        .orElse(false)
                );
        if (allMatch) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }

    private static void validateDuplicateMenu(String input) {
        String[] menuAndAmount = input.split(MENU_TYPE_DELIMITER);
        String[] menus = Arrays.stream(menuAndAmount)
                .map(entry -> entry.split(MENU_AMOUNT_DELIMITER)[MENU_INDEX])
                .toArray(String[]::new);
        if (Arrays.stream(menus).distinct().count() != menus.length) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }

    private static void processMenuEntry(String entry, Map<Optional<MenuItem>, Integer> menuAndAmountMap) {
        String[] parts = entry.split(MENU_AMOUNT_DELIMITER);
        Integer amount = 0;
        try {
            amount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
        } catch (NumberFormatException exception) {
            throw PlannerException.of(REQUEST_INVALID_MENU, exception);
        }
        if (parts.length == SEPARATE_TWO) {
            int eachAmount = Integer.parseInt(parts[AMOUNT_INDEX].trim());
            validateAmount(eachAmount);
            String menu = parts[MENU_INDEX].trim();
            Optional<MenuItem> item = findMenuItem(menu);
            menuAndAmountMap.put(item, amount);
        }
    }


    private static void validateAmount(int amount) {
        if (AMOUNT_INDEX <= amount && amount <= AMOUNT_MAX) {
            return;
        }
        throw PlannerException.from(AMOUNT_OUT_OF_RANGE);
    }

    private static void validateEmpty(Map<Optional<MenuItem>, Integer> menuAndAmountMap) {
        if (menuAndAmountMap.isEmpty()) {
            throw PlannerException.from(REQUEST_INVALID_MENU);
        }
    }

    private static void validateOmittedArgument(String input) {
        validateWhiteSpace(input);
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
