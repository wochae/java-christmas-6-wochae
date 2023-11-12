package christmas.exception;

public class DateException extends IllegalArgumentException {
    private DateException(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        super(errorMessage.getMessage(), exception);
    }

    private DateException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static DateException of(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        return new DateException(errorMessage, exception);
    }

    public static DateException from(ErrorMessage errorMessage) {
        return new DateException(errorMessage);
    }
}
