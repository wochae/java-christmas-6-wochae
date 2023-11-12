package christmas.exception;

public enum ErrorMessage {
    REQUEST_NOT_INTEGER("정수형으로 입력되어야 합니다."),
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
