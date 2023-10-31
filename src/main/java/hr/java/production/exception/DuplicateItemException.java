package hr.java.production.exception;

/**
 * The type Duplicate item exception.
 */
public class DuplicateItemException extends Exception {
    /**
     * Instantiates a new Duplicate item exception.
     */
    public DuplicateItemException() {
    }

    /**
     * Instantiates a new Duplicate item exception.
     *
     * @param message the message
     */
    public DuplicateItemException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Duplicate item exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Duplicate item exception.
     *
     * @param cause the cause
     */
    public DuplicateItemException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Duplicate item exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public DuplicateItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
