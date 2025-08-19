package dev.firecrawl.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.firecrawl.exception.ApiException;
import okhttp3.*;

import java.io.IOException;

/**
 * Utility class for HTTP request handling.
 */
public class HttpUtils {
    private static final Gson gson = new Gson();
    private static final MediaType JSON = MediaType.parse("application/json");

    /**
     * Builds an HTTP request.
     *
     * @param baseUrl the base URL
     * @param path the path
     * @param apiKey the API key
     * @param body the request body (can be null for GET requests)
     * @param idempotencyKey the idempotency key (can be null)
     * @param method the HTTP method
     * @return the built request
     */
    public static Request buildRequest(String baseUrl, String path, String apiKey, 
                                      JsonObject body, String idempotencyKey, String method) {
        Request.Builder builder = new Request.Builder().url(baseUrl + path);
        
        // Add authorization header
        builder.header("Authorization", "Bearer " + apiKey);
        
        // Add idempotency key if provided
        if (idempotencyKey != null && !idempotencyKey.isEmpty()) {
            builder.header("Idempotency-Key", idempotencyKey);
        }
        
        // Set method and body
        if ("GET".equalsIgnoreCase(method)) {
            builder.get();
        } else if ("DELETE".equalsIgnoreCase(method)) {
            RequestBody requestBody = body != null
                    ? RequestBody.create(JSON, gson.toJson(body))
                    : RequestBody.create(new byte[0], null);
            builder.delete(requestBody);
        } else {
            RequestBody requestBody = body != null
                    ? RequestBody.create(JSON, gson.toJson(body))
                    : RequestBody.create(new byte[0], null);
            builder.method(method, requestBody);
            builder.header("Content-Type", "application/json");
        }
        
        return builder.build();
    }

    /**
     * Executes an HTTP request and handles the response.
     *
     * @param client the OkHttpClient
     * @param request the request to execute
     * @param responseType the response type class
     * @param <T> the response type
     * @return the response object
     * @throws IOException if an I/O error occurs
     * @throws ApiException if the API returns an error
     */
    public static <T> T executeRequest(OkHttpClient client, Request request, Class<T> responseType) 
            throws IOException, ApiException {
        int maxRetries = 2; // total attempts = 1 + maxRetries
        int attempt = 0;
        long backoffMs = 250L;

        while (true) {
            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();

                if (!response.isSuccessful()) {
                    int code = response.code();
                    String method = request.method();
                    boolean isGet = method != null && method.equalsIgnoreCase("GET");
                    boolean retriable = isGet && (code == 502 || code == 503 || code == 504);

                    String msg = response.message();
                    // Try to parse error details from JSON body
                    try {
                        com.google.gson.JsonObject obj = gson.fromJson(responseBody, com.google.gson.JsonObject.class);
                        if (obj != null) {
                            String bodyMsg = null;
                            if (obj.has("message") && !obj.get("message").isJsonNull()) {
                                bodyMsg = obj.get("message").getAsString();
                            } else if (obj.has("error") && !obj.get("error").isJsonNull()) {
                                bodyMsg = obj.get("error").getAsString();
                            } else if (obj.has("detail") && !obj.get("detail").isJsonNull()) {
                                bodyMsg = obj.get("detail").getAsString();
                            } else if (obj.has("warning") && !obj.get("warning").isJsonNull()) {
                                bodyMsg = obj.get("warning").getAsString();
                            }
                            if (bodyMsg != null && !bodyMsg.isEmpty()) {
                                msg = (msg == null || msg.isEmpty()) ? bodyMsg : (msg + ": " + bodyMsg);
                            }
                        }
                    } catch (Exception ignored) {
                        // Ignore JSON parsing errors for error body
                    }

                    if (retriable && attempt < maxRetries) {
                        attempt++;
                        try {
                            Thread.sleep(backoffMs);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            // Fall through to throw as ApiException
                        }
                        backoffMs *= 2; // exponential backoff
                        continue; // retry the request
                    }

                    String finalMsg = (msg == null || msg.isEmpty()) ? ("HTTP " + code) : ("HTTP " + code + " - " + msg);
                    throw new ApiException(finalMsg, code, responseBody);
                }

                return gson.fromJson(responseBody, responseType);
            }
        }
    }
}