package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from a scrape request.
 */
public class ScrapeResponse extends BaseResponse {
    private FirecrawlDocument data;

    /**
     * Returns the scraped document.
     *
     * @return the scraped document
     */
    public FirecrawlDocument getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ScrapeResponse that = (ScrapeResponse) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    @Override
    public String toString() {
        return "ScrapeResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", data=" + data +
                '}';
    }
}