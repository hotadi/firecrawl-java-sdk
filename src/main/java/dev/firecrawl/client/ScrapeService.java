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
            if (params.getFormatsAny() != null) body.add("formats", gson.toJsonTree(params.getFormatsAny()));
            else if (params.getFormats() != null) body.add("formats", gson.toJsonTree(params.getFormats()));
            if (params.getHeaders() != null) body.add("headers", gson.toJsonTree(params.getHeaders()));
            if (params.getIncludeTags() != null) body.add("includeTags", gson.toJsonTree(params.getIncludeTags()));
            if (params.getExcludeTags() != null) body.add("excludeTags", gson.toJsonTree(params.getExcludeTags()));
            if (params.getOnlyMainContent() != null) body.addProperty("onlyMainContent", params.getOnlyMainContent());
            if (params.getWaitFor() != null) body.addProperty("waitFor", params.getWaitFor());
            // v2 parsers preferred over parsePDF
            if (params.getParsers() != null) body.add("parsers", gson.toJsonTree(params.getParsers()));
            if (params.getParsePDF() != null) body.addProperty("parsePDF", params.getParsePDF());
            if (params.getTimeout() != null) body.addProperty("timeout", params.getTimeout());
            // v2 additions
            if (params.getMaxAge() != null) body.addProperty("maxAge", params.getMaxAge());
            if (params.getMobile() != null) body.addProperty("mobile", params.getMobile());
            if (params.getSkipTlsVerification() != null) body.addProperty("skipTlsVerification", params.getSkipTlsVerification());
            if (params.getActions() != null) body.add("actions", gson.toJsonTree(params.getActions()));
            if (params.getLocation() != null) body.add("location", gson.toJsonTree(params.getLocation()));
            if (params.getRemoveBase64Images() != null) body.addProperty("removeBase64Images", params.getRemoveBase64Images());
            if (params.getBlockAds() != null) body.addProperty("blockAds", params.getBlockAds());
            if (params.getProxy() != null) body.addProperty("proxy", params.getProxy());
            if (params.getStoreInCache() != null) body.addProperty("storeInCache", params.getStoreInCache());
            if (params.getZeroDataRetention() != null) body.addProperty("zeroDataRetention", params.getZeroDataRetention());
        }
        
        Request request = buildRequest("/v2/scrape", body);
        ScrapeResponse response = executeRequest(request, ScrapeResponse.class);
        
        if (!response.isSuccess()) {
            throw new FirecrawlException("Scrape failed: " + response.getWarning());
        }
        
        return response.getData();
    }
}