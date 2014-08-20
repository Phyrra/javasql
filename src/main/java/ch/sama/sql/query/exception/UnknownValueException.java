package ch.sama.sql.query.exception;

public class UnknownValueException extends RuntimeException {
    public UnknownValueException() {
        super();
    }

    public UnknownValueException(String message) {
        super(message);
    }

    public UnknownValueException(Throwable cause) {
        super(cause);
    }

    public UnknownValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
