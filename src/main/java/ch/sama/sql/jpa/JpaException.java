package ch.sama.sql.jpa;

public class JpaException extends RuntimeException {
    public JpaException() {
        super();
    }

    public JpaException(String message) {
        super(message);
    }

    public JpaException(Throwable cause) {
        super(cause);
    }

    public JpaException(String message, Throwable cause) {
        super(message, cause);
    }
}
