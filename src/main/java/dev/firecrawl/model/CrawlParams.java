package dev.firecrawl.model;

import java.util.Objects;

/**
 * Parameters for crawl requests.
 */
public class CrawlParams extends BaseParams<CrawlParams> {
    private ScrapeParams scrapeOptions;
    // v2 options
    private String prompt;
    private Boolean crawlEntireDomain;
    private Integer maxDiscoveryDepth;
    private String sitemap; // "only" | "skip" | "include"

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

    /**
     * v2: Returns the natural language prompt used to derive crawl params.
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * v2: Sets the prompt.
     */
    public CrawlParams setPrompt(String prompt) {
        this.prompt = prompt;
        return self();
    }

    /**
     * v2: Returns whether to crawl entire domain.
     */
    public Boolean getCrawlEntireDomain() {
        return crawlEntireDomain;
    }

    /**
     * v2: Sets whether to crawl entire domain.
     */
    public CrawlParams setCrawlEntireDomain(Boolean crawlEntireDomain) {
        this.crawlEntireDomain = crawlEntireDomain;
        return self();
    }

    /**
     * v2: Returns the max discovery depth.
     */
    public Integer getMaxDiscoveryDepth() {
        return maxDiscoveryDepth;
    }

    /**
     * v2: Sets the max discovery depth.
     */
    public CrawlParams setMaxDiscoveryDepth(Integer maxDiscoveryDepth) {
        this.maxDiscoveryDepth = maxDiscoveryDepth;
        return self();
    }

    /**
     * v2: Returns sitemap handling ("only" | "skip" | "include").
     */
    public String getSitemap() {
        return sitemap;
    }

    /**
     * v2: Sets sitemap handling.
     */
    public CrawlParams setSitemap(String sitemap) {
        this.sitemap = sitemap;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CrawlParams that = (CrawlParams) o;
        return Objects.equals(scrapeOptions, that.scrapeOptions) &&
                Objects.equals(prompt, that.prompt) &&
                Objects.equals(crawlEntireDomain, that.crawlEntireDomain) &&
                Objects.equals(maxDiscoveryDepth, that.maxDiscoveryDepth) &&
                Objects.equals(sitemap, that.sitemap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), scrapeOptions, prompt, crawlEntireDomain, maxDiscoveryDepth, sitemap);
    }

    @Override
    public String toString() {
        return "CrawlParams{" +
                "scrapeOptions=" + scrapeOptions +
                ", prompt='" + prompt + '\'' +
                ", crawlEntireDomain=" + crawlEntireDomain +
                ", maxDiscoveryDepth=" + maxDiscoveryDepth +
                ", sitemap='" + sitemap + '\'' +
                '}';
    }
}