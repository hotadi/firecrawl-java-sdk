package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.FirecrawlDocument;
import dev.firecrawl.model.ScrapeParams;
import dev.firecrawl.model.ScrapeResponse;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for scrape-related API endpoints.
 */
class ScrapeService extends BaseService {
    /**
     * Creates a new ScrapeService with the specified client.
     *
     * @param client the FirecrawlClient
     */
    ScrapeService(FirecrawlClient client) {
        super(client);
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
    FirecrawlDocument scrapeURL(String url, ScrapeParams params) throws IOException, FirecrawlException {
        Objects.requireNonNull(url, "URL must not be null");
        
        if (params != null) {
            try {
                params.validate();
            } catch (ValidationException e) {
                throw new FirecrawlException("Invalid scrape parameters: " + e.getMessage(), e);
            }
        }
        
        JsonObject body = new JsonObject();
        body.addProperty("url", url);
        
        if (params != null) {
            if (params.getFormats() != null) body.add("formats", gson.toJsonTree(params.getFormats()));
            if (params.getHeaders() != null) body.add("headers", gson.toJsonTree(params.getHeaders()));
            if (params.getIncludeTags() != null) body.add("includeTags", gson.toJsonTree(params.getIncludeTags()));
            if (params.getExcludeTags() != null) body.add("excludeTags", gson.toJsonTree(params.getExcludeTags()));
            if (params.getOnlyMainContent() != null) body.addProperty("onlyMainContent", params.getOnlyMainContent());
            if (params.getWaitFor() != null) body.addProperty("waitFor", params.getWaitFor());
            if (params.getParsePDF() != null) body.addProperty("parsePDF", params.getParsePDF());
            if (params.getTimeout() != null) body.addProperty("timeout", params.getTimeout());
        }
        
        Request request = buildRequest("/v1/scrape", body);
        ScrapeResponse response = executeRequest(request, ScrapeResponse.class);
        
        if (!response.isSuccess()) {
            throw new FirecrawlException("Scrape failed: " + response.getWarning());
        }
        
        return response.getData();
    }
}