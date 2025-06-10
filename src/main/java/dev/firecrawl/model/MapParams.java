package dev.firecrawl.model;

import java.util.Objects;

/**
 * Parameters for map requests.
 */
public class MapParams extends BaseParams<MapParams> {
    private Boolean includeSubdomains;
    private String search;
    private Boolean ignoreSitemap;
    private Integer limit;

    /**
     * Creates a new MapParams instance.
     */
    public MapParams() {
        // Default constructor
    }

    /**
     * Returns whether to include subdomains in the map.
     *
     * @return whether to include subdomains
     */
    public Boolean getIncludeSubdomains() {
        return includeSubdomains;
    }

    /**
     * Sets whether to include subdomains in the map.
     *
     * @param includeSubdomains whether to include subdomains
     * @return this instance for method chaining
     */
    public MapParams setIncludeSubdomains(Boolean includeSubdomains) {
        this.includeSubdomains = includeSubdomains;
        return self();
    }

    /**
     * Returns the search pattern for filtering links.
     *
     * @return the search pattern
     */
    public String getSearch() {
        return search;
    }

    /**
     * Sets the search pattern for filtering links.
     *
     * @param search the search pattern
     * @return this instance for method chaining
     */
    public MapParams setSearch(String search) {
        this.search = search;
        return self();
    }

    /**
     * Returns whether to ignore the sitemap.
     *
     * @return whether to ignore the sitemap
     */
    public Boolean getIgnoreSitemap() {
        return ignoreSitemap;
    }

    /**
     * Sets whether to ignore the sitemap.
     *
     * @param ignoreSitemap whether to ignore the sitemap
     * @return this instance for method chaining
     */
    public MapParams setIgnoreSitemap(Boolean ignoreSitemap) {
        this.ignoreSitemap = ignoreSitemap;
        return self();
    }

    /**
     * Returns the maximum number of links to return.
     *
     * @return the maximum number of links
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the maximum number of links to return.
     *
     * @param limit the maximum number of links
     * @return this instance for method chaining
     */
    public MapParams setLimit(Integer limit) {
        this.limit = limit;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MapParams mapParams = (MapParams) o;
        return Objects.equals(includeSubdomains, mapParams.includeSubdomains) &&
                Objects.equals(search, mapParams.search) &&
                Objects.equals(ignoreSitemap, mapParams.ignoreSitemap) &&
                Objects.equals(limit, mapParams.limit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), includeSubdomains, search, ignoreSitemap, limit);
    }

    @Override
    public String toString() {
        return "MapParams{" +
                "includeSubdomains=" + includeSubdomains +
                ", search='" + search + '\'' +
                ", ignoreSitemap=" + ignoreSitemap +
                ", limit=" + limit +
                '}';
    }
}