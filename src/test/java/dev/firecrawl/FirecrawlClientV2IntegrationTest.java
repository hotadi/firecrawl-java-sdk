package dev.firecrawl;

import dev.firecrawl.client.FirecrawlClient;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class FirecrawlClientV2IntegrationTest {
    private static String apiKey;
    private static String endpoint;
    private static FirecrawlClient client;

    @BeforeAll
    public static void setup() {
        apiKey = System.getProperty("firecrawl.api-key",
                System.getenv("FIRECRAWL_API_KEY"));
        assumeTrue(apiKey != null && !apiKey.isEmpty(),
                "FIRECRAWL_API_KEY must be set for integration tests");
        endpoint = System.getProperty("firecrawl.endpoint",
                System.getenv("FIRECRAWL_ENDPOINT"));
        if (endpoint == null || endpoint.isEmpty()) {
            endpoint = "https://api.firecrawl.dev";
        }
        client = new FirecrawlClient(apiKey, endpoint, Duration.ofSeconds(120));
    }

    @Test
    public void testSearchV2Integration() throws IOException, FirecrawlException {
        SearchResponse resp = client.search("example domain");
        assertNotNull(resp, "SearchResponse should not be null");
        List<SearchResult> results = resp.getResults();
        assertNotNull(results, "Results list should not be null");
        assertTrue(results.size() > 0, "Expected at least one search result");
        SearchResult first = results.get(0);
        assertNotNull(first.getTitle());
        assertNotNull(first.getUrl());
    }

    @Test
    public void testScrapeV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        ScrapeParams params = new ScrapeParams();
        FirecrawlDocument doc = client.scrape(url, params);
        assertNotNull(doc, "Scraped document should not be null");
        String text = doc.getText();
        assertNotNull(text, "Document text should not be null");
        assertTrue(text.length() > 0, "Expected non-empty text content");
    }

    @Test
    public void testMapV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        MapParams params = new MapParams();
        MapResponse resp = client.map(url, params);
        assertNotNull(resp, "MapResponse should not be null");
        assertTrue(resp.isSuccess(), "Map should succeed");
        assertNotNull(resp.getLinks(), "Links array should not be null");
    }

    @Test
    public void testStartAndGetCrawlV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        CrawlParams params = new CrawlParams();
        CrawlResponse start = client.startCrawl(url, params);
        assertNotNull(start, "CrawlResponse should not be null");
        assertTrue(start.isSuccess(), "Start crawl should report success");
        assertNotNull(start.getId(), "Crawl job ID should not be null");

        CrawlStatusResponse status = client.getCrawlStatus(start.getId());
        assertNotNull(status, "Status response should not be null");
        assertNotNull(status.getStatus(), "Status field should not be null");
    }

    @Test
    public void testCrawlWaiterV2Integration() throws IOException, FirecrawlException {
        String url = "https://www.wikipedia.org";
        CrawlParams params = new CrawlParams();
        CrawlStatusResponse result = client.crawl(url, params, 5);
        assertNotNull(result, "CrawlStatusResponse should not be null");
        assertNotNull(result.getStatus(), "Crawl status should not be null");
    }

    @Test
    public void testCancelCrawlV2Integration() throws IOException, FirecrawlException {
        String url = "https://example.com";
        CrawlParams params = new CrawlParams();
        CrawlResponse start = client.startCrawl(url, params);
        assertNotNull(start.getId());
        CancelCrawlJobResponse cancel = client.cancelCrawl(start.getId());
        assertNotNull(cancel, "Cancel response should not be null");
        assertNotNull(cancel.getStatus(), "Cancel status should not be null");
    }

    @Test
    public void testSearchV2WithSourcesIntegration() throws IOException, FirecrawlException {
        SearchParams options = new SearchParams("example domain");
        options.setSources(new String[]{"web"});
        SearchResponse resp = client.search("example domain", options);
        assertNotNull(resp, "SearchResponse should not be null");
        assertNotNull(resp.getResults(), "Results should not be null");
    }

    @Test
    public void testCrawlParamsPreviewV2Integration() throws IOException, FirecrawlException {
        com.google.gson.JsonObject preview = client.crawlParamsPreview(
                "https://docs.firecrawl.dev",
                "Extract docs and blog");
        assertNotNull(preview, "Preview response should not be null");
    }
}