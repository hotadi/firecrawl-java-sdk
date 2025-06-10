package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from an asynchronous crawl request.
 */
public class CrawlResponse extends BaseResponse {
    private String id;

    /**
     * Returns the ID of the crawl job.
     *
     * @return the crawl job ID
     */
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlResponse that = (CrawlResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "CrawlResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}