package dev.firecrawl.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.firecrawl.exception.ApiException;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.util.HttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

/**
 * Base class for all API service classes.
 */
abstract class BaseService {
    protected final FirecrawlClient client;
    protected final String apiKey;
    protected final String apiUrl;
    protected final OkHttpClient httpClient;
    protected final Gson gson;

    /**
     * Creates a new BaseService with the specified client.
     *
     * @param client the FirecrawlClient
     */
    protected BaseService(FirecrawlClient client) {
        this.client = client;
        this.apiKey = client.getApiKey();
        this.apiUrl = client.getApiUrl();
        this.httpClient = client.getHttpClient();
        this.gson = client.getGson();
    }

    /**
     * Builds an HTTP request.
     *
     * @param path the path
     * @param body the request body
     * @return the built request
     */
    protected Request buildRequest(String path, JsonObject body) {
        return HttpUtils.buildRequest(apiUrl, path, apiKey, body, null, "POST");
    }

    /**
     * Builds an HTTP request with an idempotency key.
     *
     * @param path the path
     * @param body the request body
     * @param idempotencyKey the idempotency key
     * @return the built request
     */
    protected Request buildRequest(String path, JsonObject body, String idempotencyKey) {
        return HttpUtils.buildRequest(apiUrl, path, apiKey, body, idempotencyKey, "POST");
    }

    /**
     * Builds an HTTP request with an idempotency key and method.
     *
     * @param path the path
     * @param body the request body
     * @param idempotencyKey the idempotency key
     * @param method the HTTP method
     * @return the built request
     */
    protected Request buildRequest(String path, JsonObject body, String idempotencyKey, String method) {
        return HttpUtils.buildRequest(apiUrl, path, apiKey, body, idempotencyKey, method);
    }

    /**
     * Executes an HTTP request and handles the response.
     *
     * @param request the request to execute
     * @param responseType the response type class
     * @param <T> the response type
     * @return the response object
     * @throws IOException if an I/O error occurs
     * @throws FirecrawlException if the API returns an error
     */
    protected <T> T executeRequest(Request request, Class<T> responseType) throws IOException, FirecrawlException {
        try {
            return HttpUtils.executeRequest(httpClient, request, responseType);
        } catch (ApiException e) {
            throw new FirecrawlException("API request failed: " + e.getMessage(), e);
        }
    }
}