package christmas.exception;

public class PlannerException extends IllegalArgumentException {
    private PlannerException(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        super(errorMessage.getMessage(), exception);
    }

    private PlannerException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }

    public static PlannerException of(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        return new PlannerException(errorMessage, exception);
    }

    public static PlannerException from(ErrorMessage errorMessage) {
        return new PlannerException(errorMessage);
    }
}
