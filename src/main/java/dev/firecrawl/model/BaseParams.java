package dev.firecrawl.model;

import dev.firecrawl.exception.ValidationException;

import java.util.Objects;

/**
 * Base class for all API parameter objects.
 *
 * @param <T> the concrete parameter class type for builder pattern support
 */
public abstract class BaseParams<T extends BaseParams<T>> {
    /**
     * Validates the parameter object.
     *
     * @throws ValidationException if validation fails
     */
    public void validate() throws ValidationException {
        // Base validation logic, to be overridden by subclasses
    }

    /**
     * Returns this instance cast to the concrete parameter class type.
     *
     * @return this instance cast to the concrete parameter class type
     */
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true; // Subclasses should override and call super.equals()
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass()); // Subclasses should override and call super.hashCode()
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}"; // Subclasses should override and call super.toString()
    }
}