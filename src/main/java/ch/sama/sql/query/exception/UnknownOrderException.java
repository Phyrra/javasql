package ch.sama.sql.query.exception;

public class UnknownOrderException extends RuntimeException {
    public UnknownOrderException() {
        super();
    }

    public UnknownOrderException(String message) {
        super(message);
    }

    public UnknownOrderException(Throwable cause) {
        super(cause);
    }

    public UnknownOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
