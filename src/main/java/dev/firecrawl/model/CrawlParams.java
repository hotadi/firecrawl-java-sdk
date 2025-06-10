package dev.firecrawl.model;

import java.util.Objects;

/**
 * Parameters for crawl requests.
 */
public class CrawlParams extends BaseParams<CrawlParams> {
    private ScrapeParams scrapeOptions;

    /**
     * Creates a new CrawlParams instance.
     */
    public CrawlParams() {
        // Default constructor
    }

    /**
     * Returns the scrape options for the crawl.
     *
     * @return the scrape options
     */
    public ScrapeParams getScrapeOptions() {
        return scrapeOptions;
    }

    /**
     * Sets the scrape options for the crawl.
     *
     * @param scrapeOptions the scrape options
     * @return this instance for method chaining
     */
    public CrawlParams setScrapeOptions(ScrapeParams scrapeOptions) {
        this.scrapeOptions = scrapeOptions;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlParams that = (CrawlParams) o;
        return Objects.equals(scrapeOptions, that.scrapeOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scrapeOptions);
    }

    @Override
    public String toString() {
        return "CrawlParams{" +
                "scrapeOptions=" + scrapeOptions +
                '}';
    }
}