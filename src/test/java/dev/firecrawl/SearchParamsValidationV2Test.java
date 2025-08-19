package dev.firecrawl;

import dev.firecrawl.client.FirecrawlClient;
import dev.firecrawl.exception.FirecrawlException;
import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.SearchParams;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SearchParamsValidationV2Test {

    @Test
    public void validateRejectsEmptyQuery() {
        SearchParams sp = new SearchParams("");
        ValidationException ex = assertThrows(ValidationException.class, sp::validate);
        assertTrue(ex.getMessage().toLowerCase().contains("query"));
    }

    @Test
    public void clientSearchThrowsOnEmptyQueryWithoutNetworkCall() {
        // Construct client with dummy API key; since validation fails before HTTP, no network call occurs.
        FirecrawlClient client = new FirecrawlClient("test-key", "https://api.firecrawl.dev", Duration.ofSeconds(5));
        SearchParams sp = new SearchParams("   ");
        FirecrawlException ex = assertThrows(FirecrawlException.class, () -> client.search(sp));
        assertTrue(ex.getMessage().toLowerCase().contains("invalid search parameters"));
    }
}
