package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Response from a search request.
 */
public class SearchResponse extends BaseResponse {
    private SearchResult[] data;

    /**
     * Returns the search results as an array.
     *
     * @return the search results array
     */
    public SearchResult[] getData() {
        return data;
    }

    /**
     * Returns the search results as a list.
     *
     * @return the search results list
     */
    public List<SearchResult> getResults() {
        if (data == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SearchResponse that = (SearchResponse) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}