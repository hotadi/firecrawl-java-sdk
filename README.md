# Firecrawl Java SDK

A Java client library for the [Firecrawl API](https://firecrawl.dev), providing web crawling, scraping, and search capabilities.

## Requirements

- Java 17 or higher
- Maven 3.8+ (for building from source)

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>dev.firecrawl</groupId>
    <artifactId>firecrawl-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

Add the following to your `build.gradle`:

```groovy
implementation 'dev.firecrawl:firecrawl-java-sdk:1.0.0'
```

### Building from Source

To build and install the SDK locally:

```bash
git clone https://github.com/firecrawl/firecrawl-java-sdk.git
cd firecrawl-java-sdk
mvn install
```

## Usage

### Creating a Client

```java
import dev.firecrawl.*;
import java.time.Duration;

// Create a client with default endpoint
FirecrawlClient client = new FirecrawlClient(
        "your-api-key",
        null,  // Uses default endpoint: https://api.firecrawl.dev
        Duration.ofSeconds(60)  // Request timeout
);

        // Or specify a custom endpoint
        FirecrawlClient client = new FirecrawlClient(
                "your-api-key",
                "https://custom-api-endpoint.example.com",
                Duration.ofSeconds(120)
        );

        // You can also set the API key via the FIRECRAWL_API_KEY environment variable
        FirecrawlClient client = new FirecrawlClient(
                null,  // Will use FIRECRAWL_API_KEY environment variable
                null,
                Duration.ofSeconds(120)  // Default timeout is 120 seconds
        );
```

### Web Scraping

```java
// Simple scraping
FirecrawlDocument doc = client.scrapeURL("https://example.com", null);
System.out.println(doc.getHtml());
        System.out.println(doc.getText());

// Advanced scraping with options
ScrapeParams params = new ScrapeParams();
params.setOnlyMainContent(true);  // Extract only main content
params.setWaitFor(5000);          // Wait 5 seconds after page load
FirecrawlDocument doc = client.scrapeURL("https://example.com", params);
```

### Search

```java
// Basic search
SearchParams params = new SearchParams("open source java sdk");
params.setLimit(10);
params.setLang("en");
SearchResponse resp = client.search(params);

// Process results
if (resp.isSuccess()) {
        for (SearchResult result : resp.getResults()) {
        System.out.println(result.getTitle() + " - " + result.getUrl());
        }
        }

// Check for warnings
        if (resp.getWarning() != null) {
        System.err.println("Warning: " + resp.getWarning());
        }
```

### Web Crawling

```java
// Asynchronous crawling
String idempotencyKey = java.util.UUID.randomUUID().toString();
CrawlParams params = new CrawlParams();
CrawlResponse resp = client.asyncCrawlURL("https://example.com", params, idempotencyKey);
String jobId = resp.getId();

// Check crawl status
CrawlStatusResponse status = client.checkCrawlStatus(jobId);
System.out.println("Crawl status: " + status.getStatus());

// Synchronous crawling (with polling)
CrawlStatusResponse result = client.crawlURL("https://example.com", params, idempotencyKey, 5);
if ("completed".equals(result.getStatus())) {
FirecrawlDocument[] documents = result.getData();
// Process crawled documents
}

// Cancel a crawl job
CancelCrawlJobResponse cancelResp = client.cancelCrawlJob(jobId);
```

### URL Mapping

```java
MapParams params = new MapParams();
MapResponse resp = client.mapURL("https://example.com", params);
if (resp.isSuccess()) {
String[] links = resp.getLinks();
// Process links
}
```

## API Documentation

For detailed API documentation, visit [https://firecrawl.dev/docs](https://firecrawl.dev/docs).

## License

This SDK is available under the MIT License. See the LICENSE file for more information.