package dev.firecrawl.exception;

/**
 * Exception thrown when parameter validation fails.
 */
public class ValidationException extends FirecrawlException {
    private final String paramName;

    /**
     * Constructs a new ValidationException with the specified detail message and parameter name.
     *
     * @param message the detail message
     * @param paramName the name of the parameter that failed validation
     */
    public ValidationException(String message, String paramName) {
        super(message);
        this.paramName = paramName;
    }

    /**
     * Constructs a new ValidationException with the specified detail message, cause, and parameter name.
     *
     * @param message the detail message
     * @param cause the cause
     * @param paramName the name of the parameter that failed validation
     */
    public ValidationException(String message, Throwable cause, String paramName) {
        super(message, cause);
        this.paramName = paramName;
    }

    /**
     * Returns the name of the parameter that failed validation.
     *
     * @return the parameter name
     */
    public String getParamName() {
        return paramName;
    }
}