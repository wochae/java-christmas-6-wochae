package christmas.controller;

import static christmas.domain.booking.constants.Message.REQUEST_MENU_AND_AMOUNT;

import christmas.domain.booking.dto.MenuItem;
import christmas.exception.PlannerException;
import christmas.service.Parser;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.Map;

public class MenuController {
    public MenuController() {
    }

    public static Map<MenuItem, Integer> requestMenu() {
        OutputView.print(REQUEST_MENU_AND_AMOUNT);
        return readMenuInput();
    }

    private static Map<MenuItem, Integer> readMenuInput() {
        try {
            final String input = InputView.readLine();
            return Parser.splitMenuAndAmount(input);
        } catch (PlannerException exception) {
            OutputView.println(exception.getMessage());
            return readMenuInput();
        }
    }
}
