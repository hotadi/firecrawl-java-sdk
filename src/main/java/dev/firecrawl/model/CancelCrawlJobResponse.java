package dev.firecrawl.model;

import java.util.Objects;

/**
 * Response from a cancel crawl job request.
 */
public class CancelCrawlJobResponse extends BaseResponse {
    private String status;

    /**
     * Returns the status of the cancel operation.
     *
     * @return the cancel operation status
     */
    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CancelCrawlJobResponse that = (CancelCrawlJobResponse) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status);
    }

    @Override
    public String toString() {
        return "CancelCrawlJobResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}