package dev.firecrawl.model;

import java.util.Objects;

/**
 * Base class for all API response objects.
 */
public abstract class BaseResponse {
    private boolean success;
    private String warning;

    /**
     * Returns whether the API request was successful.
     *
     * @return true if the request was successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns any warning message from the API.
     *
     * @return the warning message, or null if there is no warning
     */
    public String getWarning() {
        return warning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseResponse that = (BaseResponse) o;
        return success == that.success && Objects.equals(warning, that.warning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, warning);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "success=" + success +
                ", warning='" + warning + '\'' +
                '}';
    }
}