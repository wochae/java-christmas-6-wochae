package christmas.controller;

import static christmas.domain.booking.constants.Message.REQUEST_NUMBER_OF_DAYS;
import static christmas.domain.booking.constants.Message.WELCOME;

import christmas.view.input.InputView;
import christmas.view.output.OutputView;

public class BookingController {
    public BookingController() {
    }

    public static int requestBooking() {
        OutputView.print(WELCOME);
        OutputView.print(REQUEST_NUMBER_OF_DAYS);
        return readNumberInput();
    }

    private static int readNumberInput() {
        try {
            final String userInput = InputView.readLine();
            return Integer.parseInt(userInput);
        } catch (IllegalArgumentException exception) {
            OutputView.println(exception.getMessage());
            return readNumberInput();
        }
    }
}
