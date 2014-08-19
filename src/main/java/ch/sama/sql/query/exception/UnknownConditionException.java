package ch.sama.sql.query.exception;

public class UnknownConditionException extends RuntimeException {
    public UnknownConditionException() {
        super();
    }

    public UnknownConditionException(String message) {
        super(message);
    }

    public UnknownConditionException(Throwable cause) {
        super(cause);
    }

    public UnknownConditionException(String message, Throwable cause) {
        super(message, cause);
    }
}
