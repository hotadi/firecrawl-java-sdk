package dev.firecrawl.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.firecrawl.exception.ApiException;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.*;
import dev.firecrawl.util.HttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

/**
 * Client for the Firecrawl API.
 */
public class FirecrawlClient {
    private final String apiKey;
    private final String apiUrl;
    private final OkHttpClient httpClient;
    private final Gson gson = new Gson();

    // Service instances
    private final SearchService searchService;
    private final ScrapeService scrapeService;
    private final MapService mapService;
    private final CrawlService crawlService;

    /**
     * Creates a new FirecrawlClient with the specified API key, API URL, and timeout.
     *
     * @param apiKey the API key
     * @param apiUrl the API URL (can be null to use the default URL)
     * @param timeout the request timeout
     */
    public FirecrawlClient(String apiKey, String apiUrl, Duration timeout) {
        this.apiKey = apiKey != null && !apiKey.isEmpty()
                ? apiKey
                : System.getenv("FIRECRAWL_API_KEY");
        if (this.apiKey == null || this.apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key must be provided");
        }
        this.apiUrl = (apiUrl != null && !apiUrl.isEmpty())
                ? apiUrl
                : System.getenv().getOrDefault("FIRECRAWL_API_URL", "https://api.firecrawl.dev");
        Duration t = timeout != null ? timeout : Duration.ofSeconds(120);
        this.httpClient = new OkHttpClient.Builder()
                .callTimeout(t)
                .build();

        // Initialize services
        this.searchService = new SearchService(this);
        this.scrapeService = new ScrapeService(this);
        this.mapService = new MapService(this);
        this.crawlService = new CrawlService(this);
    }

    /**
     * Searches for the specified query with the specified parameters.
     *
     * @param params the search parameters
     * @return the search response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public SearchResponse search(SearchParams params) throws IOException, FirecrawlException {
        return searchService.search(params);
    }

    /**
     * Searches for the specified query with the specified legacy parameters.
     *
     * @param query the search query
     * @param legacyParams the legacy parameters
     * @return the search response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     * @deprecated use {@link #search(SearchParams)} instead
     */
    @Deprecated
    public SearchResponse search(String query, JsonObject legacyParams) throws IOException, FirecrawlException {
        return searchService.search(query, legacyParams);
    }

    /**
     * Scrapes the specified URL with the specified parameters.
     *
     * @param url the URL to scrape
     * @param params the scrape parameters
     * @return the scraped document
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public FirecrawlDocument scrapeURL(String url, ScrapeParams params) throws IOException, FirecrawlException {
        return scrapeService.scrapeURL(url, params);
    }

    /**
     * Maps the specified URL with the specified parameters.
     *
     * @param url the URL to map
     * @param params the map parameters
     * @return the map response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public MapResponse mapURL(String url, MapParams params) throws IOException, FirecrawlException {
        return mapService.mapURL(url, params);
    }

    /**
     * Crawls the specified URL with the specified parameters.
     *
     * @param url the URL to crawl
     * @param params the crawl parameters
     * @param idempotencyKey the idempotency key
     * @param pollInterval the polling interval in seconds (default: 2)
     * @return the crawl status response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CrawlStatusResponse crawlURL(String url, CrawlParams params, String idempotencyKey, int... pollInterval) throws IOException, FirecrawlException {
        return crawlService.crawlURL(url, params, idempotencyKey, pollInterval);
    }

    /**
     * Asynchronously crawls the specified URL with the specified parameters.
     *
     * @param url the URL to crawl
     * @param params the crawl parameters
     * @param idempotencyKey the idempotency key
     * @return the crawl response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CrawlResponse asyncCrawlURL(String url, CrawlParams params, String idempotencyKey) throws IOException, FirecrawlException {
        return crawlService.asyncCrawlURL(url, params, idempotencyKey);
    }

    /**
     * Checks the status of the specified crawl job.
     *
     * @param id the crawl job ID
     * @return the crawl status response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CrawlStatusResponse checkCrawlStatus(String id) throws IOException, FirecrawlException {
        return crawlService.checkCrawlStatus(id);
    }

    /**
     * Cancels the specified crawl job.
     *
     * @param id the crawl job ID
     * @return the cancel crawl job response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    public CancelCrawlJobResponse cancelCrawlJob(String id) throws IOException, FirecrawlException {
        return crawlService.cancelCrawlJob(id);
    }

    /**
     * Returns the API key.
     *
     * @return the API key
     */
    String getApiKey() {
        return apiKey;
    }

    /**
     * Returns the API URL.
     *
     * @return the API URL
     */
    String getApiUrl() {
        return apiUrl;
    }

    /**
     * Returns the HTTP client.
     *
     * @return the HTTP client
     */
    OkHttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Returns the Gson instance.
     *
     * @return the Gson instance
     */
    Gson getGson() {
        return gson;
    }
}