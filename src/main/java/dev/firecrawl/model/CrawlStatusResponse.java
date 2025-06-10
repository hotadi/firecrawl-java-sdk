package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from a crawl status request.
 */
public class CrawlStatusResponse extends BaseResponse {
    private String status;
    private FirecrawlDocument[] data;

    /**
     * Returns the status of the crawl job.
     *
     * @return the crawl job status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the crawled documents.
     *
     * @return the crawled documents
     */
    public FirecrawlDocument[] getData() {
        return data;
    }

    /**
     * Checks if the crawl job is completed.
     *
     * @return true if the crawl job is completed, false otherwise
     */
    public boolean isCompleted() {
        return "completed".equalsIgnoreCase(status);
    }

    /**
     * Checks if the crawl job is running.
     *
     * @return true if the crawl job is running, false otherwise
     */
    public boolean isRunning() {
        return "running".equalsIgnoreCase(status);
    }

    /**
     * Checks if the crawl job has failed.
     *
     * @return true if the crawl job has failed, false otherwise
     */
    public boolean isFailed() {
        return "failed".equalsIgnoreCase(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlStatusResponse that = (CrawlStatusResponse) o;
        return Objects.equals(status, that.status) &&
                Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), status);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "CrawlStatusResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", status='" + status + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}