package dev.firecrawl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.firecrawl.model.SearchResponse;
import dev.firecrawl.model.SearchResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchResponseV2ParsingTest {
    private static final Gson GSON = new Gson();

    @Test
    public void parsesTopLevelArray() {
        String json = "{\n" +
                "  \"success\": true,\n" +
                "  \"data\": [ { \"title\": \"A\", \"url\": \"https://a.com\" } ]\n" +
                "}";
        SearchResponse resp = GSON.fromJson(json, SearchResponse.class);
        assertTrue(resp.isSuccess());
        assertEquals(1, resp.getResults().size());
        assertEquals("A", resp.getResults().get(0).getTitle());
    }

    @Test
    public void parsesObjectWithResultsArray() {
        String json = "{\n" +
                "  \"data\": { \"results\": [ { \"title\": \"B\", \"url\": \"https://b.com\" } ] }\n" +
                "}";
        SearchResponse resp = GSON.fromJson(json, SearchResponse.class);
        assertEquals(1, resp.getData().length);
        assertEquals("https://b.com", resp.getResults().get(0).getUrl());
    }

    @Test
    public void parsesV2WebArrayAtDataLevel() {
        String json = "{\n" +
                "  \"data\": { \"web\": [ { \"title\": \"C\", \"url\": \"https://c.com\" } ] }\n" +
                "}";
        SearchResponse resp = GSON.fromJson(json, SearchResponse.class);
        assertEquals(1, resp.getResults().size());
        assertEquals("C", resp.getResults().get(0).getTitle());
    }

    @Test
    public void parsesV2WebObjectWithOrganicResults() {
        String json = "{\n" +
                "  \"data\": { \"web\": { \"organic_results\": [ { \"title\": \"D\", \"url\": \"https://d.com\" } ] } }\n" +
                "}";
        SearchResponse resp = GSON.fromJson(json, SearchResponse.class);
        assertEquals(1, resp.getResults().size());
        assertEquals("https://d.com", resp.getResults().get(0).getUrl());
    }


    @Test
    public void fallbackSingleObjectNormalization() {
        JsonObject obj = new JsonObject();
        obj.addProperty("title", "F");
        obj.addProperty("url", "https://f.com");
        String json = "{\n  \"data\": " + obj.toString() + "\n}";
        SearchResponse resp = GSON.fromJson(json, SearchResponse.class);
        SearchResult[] data = resp.getData();
        assertEquals(1, data.length);
        assertEquals("F", data[0].getTitle());
        assertEquals("https://f.com", data[0].getUrl());
    }

    @Test
    public void handlesEmptyData() {
        String json = "{\n  \"success\": true,\n  \"data\": null\n}";
        SearchResponse resp = GSON.fromJson(json, SearchResponse.class);
        assertNotNull(resp.getResults());
        assertEquals(0, resp.getResults().size());
    }
}
