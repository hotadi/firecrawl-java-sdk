package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.CancelCrawlJobResponse;
import dev.firecrawl.model.CrawlParams;
import dev.firecrawl.model.CrawlResponse;
import dev.firecrawl.model.CrawlStatusResponse;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for crawl-related API endpoints.
 */
class CrawlService extends BaseService {
    private static final MediaType JSON = MediaType.parse("application/json");

    /**
     * Creates a new CrawlService with the specified client.
     *
     * @param client the FirecrawlClient
     */
    CrawlService(FirecrawlClient client) {
        super(client);
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
    CrawlStatusResponse crawlURL(String url, CrawlParams params, String idempotencyKey, int... pollInterval) throws IOException, FirecrawlException {
        Objects.requireNonNull(url, "URL must not be null");
        
        if (params != null) {
            try {
                params.validate();
            } catch (ValidationException e) {
                throw new FirecrawlException("Invalid crawl parameters: " + e.getMessage(), e);
            }
        }
        
        CrawlResponse response = asyncCrawlURL(url, params, idempotencyKey);
        if (!response.isSuccess()) {
            throw new FirecrawlException("Crawl failed: " + response.getWarning());
        }
        
        int interval = pollInterval.length > 0 ? pollInterval[0] : 2;
        return monitorJobStatus(response.getId(), interval);
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
    CrawlResponse asyncCrawlURL(String url, CrawlParams params, String idempotencyKey) throws IOException, FirecrawlException {
        Objects.requireNonNull(url, "URL must not be null");
        
        if (params != null) {
            try {
                params.validate();
            } catch (ValidationException e) {
                throw new FirecrawlException("Invalid crawl parameters: " + e.getMessage(), e);
            }
        }
        
        JsonObject body = new JsonObject();
        body.addProperty("url", url);
        
        if (params != null && params.getScrapeOptions() != null) {
            body.add("scrapeOptions", gson.toJsonTree(params.getScrapeOptions()));
        }
        
        Request request = buildRequest("/v1/crawl", body, idempotencyKey);
        return executeRequest(request, CrawlResponse.class);
    }

    /**
     * Checks the status of the specified crawl job.
     *
     * @param id the crawl job ID
     * @return the crawl status response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    CrawlStatusResponse checkCrawlStatus(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Crawl job ID must not be null");
        
        Request request = buildRequest("/v1/crawl/" + id, null, null, "GET");
        return executeRequest(request, CrawlStatusResponse.class);
    }

    /**
     * Cancels the specified crawl job.
     *
     * @param id the crawl job ID
     * @return the cancel crawl job response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    CancelCrawlJobResponse cancelCrawlJob(String id) throws IOException, FirecrawlException {
        Objects.requireNonNull(id, "Crawl job ID must not be null");
        
        RequestBody emptyBody = RequestBody.create(new byte[0], null);
        Request request = new Request.Builder()
                .url(apiUrl + "/v1/crawl/" + id)
                .delete(emptyBody)
                .header("Authorization", "Bearer " + apiKey)
                .build();
                
        return executeRequest(request, CancelCrawlJobResponse.class);
    }

    /**
     * Polls the crawl job until completion or failure.
     *
     * @param jobId the crawl job ID
     * @param intervalSeconds the polling interval in seconds
     * @return the final crawl status response
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    private CrawlStatusResponse monitorJobStatus(String jobId, int intervalSeconds) throws IOException, FirecrawlException {
        while (true) {
            CrawlStatusResponse status = checkCrawlStatus(jobId);
            String s = status.getStatus();
            
            if (s == null || !"running".equalsIgnoreCase(s)) {
                return status;
            }
            
            try {
                Thread.sleep(intervalSeconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new FirecrawlException("Interrupted while monitoring crawl status", e);
            }
        }
    }
}