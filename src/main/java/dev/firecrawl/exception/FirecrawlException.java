package dev.firecrawl.exception;

/**
 * Base exception class for all Firecrawl SDK exceptions.
 */
public class FirecrawlException extends Exception {
    /**
     * Constructs a new FirecrawlException with the specified detail message.
     *
     * @param message the detail message
     */
    public FirecrawlException(String message) {
        super(message);
    }

    /**
     * Constructs a new FirecrawlException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public FirecrawlException(String message, Throwable cause) {
        super(message, cause);
    }
}