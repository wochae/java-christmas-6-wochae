package christmas.view.output;

import christmas.domain.booking.constants.Message;

public class OutputView {
    public static void println(final Object data) {
        System.out.println(data);
    }

    public static void print(final Message message) {
        println(message.getMessage());
    }
}