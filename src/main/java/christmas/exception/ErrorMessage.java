package christmas.exception;

public enum ErrorMessage {
    REQUEST_INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    REQUEST_INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),

    NUMBER_OUT_OF_RANGE("숫자가 범위를 벗어났습니다. 다시 입력해 주세요."),
    AMOUNT_OUT_OF_RANGE("메뉴의 개수가 범위를 벗어났습니다. 다시 입력해 주세요."),
    DATE_OUT_OF_RANGE("12월 중 날짜를 입력해 주세요!");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
