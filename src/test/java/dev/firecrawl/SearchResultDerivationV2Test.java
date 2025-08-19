package dev.firecrawl;

import com.google.gson.Gson;
import dev.firecrawl.model.SearchResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchResultDerivationV2Test {
    private static final Gson GSON = new Gson();

    @Test
    public void derivesTitleFromMetadataWhenMissing() {
        String json = "{\n" +
                "  \"description\": \"desc\",\n" +
                "  \"metadata\": { \"title\": \"Meta Title\" }\n" +
                "}";
        SearchResult r = GSON.fromJson(json, SearchResult.class);
        assertEquals("Meta Title", r.getTitle());
        assertEquals("desc", r.getDescription());
    }

    @Test
    public void derivesTitleFromMarkdownHeading() {
        String json = "{\n" +
                "  \"markdown\": \"# Heading\nBody line\"\n" +
                "}";
        SearchResult r = GSON.fromJson(json, SearchResult.class);
        assertEquals("Heading", r.getTitle());
        assertEquals("Body line", r.getDescription());
    }

    @Test
    public void derivesUrlFromMetadataThenLinks() {
        String jsonMeta = "{\n" +
                "  \"metadata\": { \"sourceURL\": \"https://example.com\" }\n" +
                "}";
        SearchResult r1 = GSON.fromJson(jsonMeta, SearchResult.class);
        assertEquals("https://example.com", r1.getUrl());

        String jsonLinks = "{\n" +
                "  \"links\": [\"not-a-url\", \"https://another.com/page\"]\n" +
                "}";
        SearchResult r2 = GSON.fromJson(jsonLinks, SearchResult.class);
        assertEquals("https://another.com/page", r2.getUrl());
    }

    @Test
    public void respectsExplicitFieldsOverDerived() {
        String json = "{\n" +
                "  \"title\": \"Explicit\",\n" +
                "  \"description\": \"Explicit desc\",\n" +
                "  \"url\": \"https://explicit.com\",\n" +
                "  \"markdown\": \"# Heading\nBody\",\n" +
                "  \"metadata\": { \"title\": \"Meta\", \"sourceURL\": \"https://meta.com\" }\n" +
                "}";
        SearchResult r = GSON.fromJson(json, SearchResult.class);
        assertEquals("Explicit", r.getTitle());
        assertEquals("Explicit desc", r.getDescription());
        assertEquals("https://explicit.com", r.getUrl());
    }
}
