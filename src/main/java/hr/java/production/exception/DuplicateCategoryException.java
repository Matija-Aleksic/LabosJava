package hr.java.production.exception;

/**
 * The type Duplicate category exception.
 */
public class DuplicateCategoryException extends RuntimeException {
    /**
     * Instantiates a new Duplicate category exception.
     *
     * @param message the message
     */
    public DuplicateCategoryException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Duplicate category exception.
     *
     * @param cause the cause
     */
    public DuplicateCategoryException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Duplicate category exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public DuplicateCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Instantiates a new Duplicate category exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DuplicateCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Duplicate category exception.
     */
    public DuplicateCategoryException() {
    }
}
