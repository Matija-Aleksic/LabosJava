package hr.java.production.exception;

/**
 * The type Grad nepostoji excepion.
 */
public class GradNepostojiExcepion extends Exception{
    /**
     * Instantiates a new Grad nepostoji excepion.
     */
    public GradNepostojiExcepion() {
    }

    /**
     * Instantiates a new Grad nepostoji excepion.
     *
     * @param message the message
     */
    public GradNepostojiExcepion(String message) {
        super(message);
    }

    /**
     * Instantiates a new Grad nepostoji excepion.
     *
     * @param message the message
     * @param cause   the cause
     */
    public GradNepostojiExcepion(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Grad nepostoji excepion.
     *
     * @param cause the cause
     */
    public GradNepostojiExcepion(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Grad nepostoji excepion.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public GradNepostojiExcepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
