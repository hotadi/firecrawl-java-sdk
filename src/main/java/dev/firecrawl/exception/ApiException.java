package dev.firecrawl.exception;

/**
 * Exception thrown when an API request fails.
 */
public class ApiException extends FirecrawlException {
    private final int statusCode;
    private final String responseBody;

    /**
     * Constructs a new ApiException with the specified detail message, status code, and response body.
     *
     * @param message the detail message
     * @param statusCode the HTTP status code
     * @param responseBody the response body
     */
    public ApiException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    /**
     * Constructs a new ApiException with the specified detail message, cause, status code, and response body.
     *
     * @param message the detail message
     * @param cause the cause
     * @param statusCode the HTTP status code
     * @param responseBody the response body
     */
    public ApiException(String message, Throwable cause, int statusCode, String responseBody) {
        super(message, cause);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    /**
     * Returns the HTTP status code.
     *
     * @return the HTTP status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Returns the response body.
     *
     * @return the response body
     */
    public String getResponseBody() {
        return responseBody;
    }
}