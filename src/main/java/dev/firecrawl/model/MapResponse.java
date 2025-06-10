package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Response from a map request.
 */
public class MapResponse extends BaseResponse {
    private String[] links;

    /**
     * Returns the links found in the map.
     *
     * @return the links
     */
    public String[] getLinks() {
        return links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MapResponse that = (MapResponse) o;
        return Arrays.equals(links, that.links);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(links);
        return result;
    }

    @Override
    public String toString() {
        return "MapResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", links=" + Arrays.toString(links) +
                '}';
    }
}