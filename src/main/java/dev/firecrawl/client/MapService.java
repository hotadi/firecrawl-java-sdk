package dev.firecrawl.client;

import com.google.gson.JsonObject;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.MapParams;
import dev.firecrawl.model.MapResponse;
import okhttp3.Request;

import java.io.IOException;
import java.util.Objects;

/**
 * Service for map-related API endpoints.
 */
class MapService extends BaseService {
    /**
     * Creates a new MapService with the specified client.
     *
     * @param client the FirecrawlClient
     */
    MapService(FirecrawlClient client) {
        super(client);
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
    MapResponse mapURL(String url, MapParams params) throws IOException, FirecrawlException {
        Objects.requireNonNull(url, "URL must not be null");
        
        if (params != null) {
            try {
                params.validate();
            } catch (ValidationException e) {
                throw new FirecrawlException("Invalid map parameters: " + e.getMessage(), e);
            }
        }
        
        JsonObject body = new JsonObject();
        body.addProperty("url", url);
        
        if (params != null) {
            if (params.getIncludeSubdomains() != null) body.addProperty("includeSubdomains", params.getIncludeSubdomains());
            if (params.getSearch() != null) body.addProperty("search", params.getSearch());
            if (params.getIgnoreSitemap() != null) body.addProperty("ignoreSitemap", params.getIgnoreSitemap());
            if (params.getLimit() != null) body.addProperty("limit", params.getLimit());
        }
        
        Request request = buildRequest("/v1/map", body);
        MapResponse response = executeRequest(request, MapResponse.class);
        
        if (!response.isSuccess()) {
            throw new FirecrawlException("Map failed: " + response.getWarning());
        }
        
        return response;
    }
}