package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Parameters for scrape requests.
 */
public class ScrapeParams extends BaseParams<ScrapeParams> {
    private String[] formats;
    // v2: allow object formats (e.g., { type: "json", prompt, schema } or screenshot object)
    private Object[] formatsAny;
    private Map<String, String> headers;
    private String[] includeTags;
    private String[] excludeTags;
    private Boolean onlyMainContent;
    private Integer waitFor;
    private Boolean parsePDF;
    // v2: parsers field (e.g., ["pdf"] or objects like { type: "pdf" })
    private Object[] parsers;
    private Integer timeout;

    // v2 additions
    private Integer maxAge;
    private Boolean mobile;
    private Boolean skipTlsVerification;
    private Object[] actions;
    private Map<String, Object> location;
    private Boolean removeBase64Images;
    private Boolean blockAds;
    private String proxy;
    private Boolean storeInCache;
    private Boolean zeroDataRetention;

    /**
     * Creates a new ScrapeParams instance.
     */
    public ScrapeParams() {
        // Default constructor
    }

    /**
     * Returns the output formats.
     *
     * @return the output formats
     */
    public String[] getFormats() {
        return formats;
    }

    /**
     * Sets the output formats.
     *
     * @param formats the output formats
     * @return this instance for method chaining
     */
    public ScrapeParams setFormats(String[] formats) {
        this.formats = formats;
        return self();
    }

    /**
     * v2: Returns the output formats allowing object entries.
     *
     * @return the output formats (any type)
     */
    public Object[] getFormatsAny() {
        return formatsAny;
    }

    /**
     * v2: Sets the output formats allowing object entries.
     *
     * @param formatsAny the output formats (any type)
     * @return this instance for method chaining
     */
    public ScrapeParams setFormatsAny(Object[] formatsAny) {
        this.formatsAny = formatsAny;
        return self();
    }

    /**
     * Returns the HTTP headers to send with the request.
     *
     * @return the HTTP headers
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * Sets the HTTP headers to send with the request.
     *
     * @param headers the HTTP headers
     * @return this instance for method chaining
     */
    public ScrapeParams setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return self();
    }

    /**
     * Returns the HTML tags to include in the output.
     *
     * @return the HTML tags to include
     */
    public String[] getIncludeTags() {
        return includeTags;
    }

    /**
     * Sets the HTML tags to include in the output.
     *
     * @param includeTags the HTML tags to include
     * @return this instance for method chaining
     */
    public ScrapeParams setIncludeTags(String[] includeTags) {
        this.includeTags = includeTags;
        return self();
    }

    /**
     * Returns the HTML tags to exclude from the output.
     *
     * @return the HTML tags to exclude
     */
    public String[] getExcludeTags() {
        return excludeTags;
    }

    /**
     * Sets the HTML tags to exclude from the output.
     *
     * @param excludeTags the HTML tags to exclude
     * @return this instance for method chaining
     */
    public ScrapeParams setExcludeTags(String[] excludeTags) {
        this.excludeTags = excludeTags;
        return self();
    }

    /**
     * Returns whether to extract only the main content.
     *
     * @return whether to extract only the main content
     */
    public Boolean getOnlyMainContent() {
        return onlyMainContent;
    }

    /**
     * Sets whether to extract only the main content.
     *
     * @param onlyMainContent whether to extract only the main content
     * @return this instance for method chaining
     */
    public ScrapeParams setOnlyMainContent(Boolean onlyMainContent) {
        this.onlyMainContent = onlyMainContent;
        return self();
    }

    /**
     * Returns the time to wait after page load (in milliseconds).
     *
     * @return the wait time in milliseconds
     */
    public Integer getWaitFor() {
        return waitFor;
    }

    /**
     * Sets the time to wait after page load (in milliseconds).
     *
     * @param waitFor the wait time in milliseconds
     * @return this instance for method chaining
     */
    public ScrapeParams setWaitFor(Integer waitFor) {
        this.waitFor = waitFor;
        return self();
    }

    /**
     * Returns whether to parse PDF files.
     *
     * @return whether to parse PDF files
     */
    public Boolean getParsePDF() {
        return parsePDF;
    }

    /**
     * Sets whether to parse PDF files.
     *
     * @param parsePDF whether to parse PDF files
     * @return this instance for method chaining
     */
    public ScrapeParams setParsePDF(Boolean parsePDF) {
        this.parsePDF = parsePDF;
        return self();
    }

    /**
     * Returns the timeout in milliseconds.
     *
     * @return the timeout in milliseconds
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Sets the timeout in milliseconds.
     *
     * @param timeout the timeout in milliseconds
     * @return this instance for method chaining
     */
    public ScrapeParams setTimeout(Integer timeout) {
        this.timeout = timeout;
        return self();
    }

    /**
     * v2: Returns the parsers array.
     *
     * @return parsers
     */
    public Object[] getParsers() {
        return parsers;
    }

    /**
     * v2: Sets the parsers array.
     *
     * @param parsers parsers (strings or objects)
     * @return this instance for method chaining
     */
    public ScrapeParams setParsers(Object[] parsers) {
        this.parsers = parsers;
        return self();
    }

    // v2 additions: getters and setters
    public Integer getMaxAge() { return maxAge; }
    public ScrapeParams setMaxAge(Integer maxAge) { this.maxAge = maxAge; return self(); }

    public Boolean getMobile() { return mobile; }
    public ScrapeParams setMobile(Boolean mobile) { this.mobile = mobile; return self(); }

    public Boolean getSkipTlsVerification() { return skipTlsVerification; }
    public ScrapeParams setSkipTlsVerification(Boolean skipTlsVerification) { this.skipTlsVerification = skipTlsVerification; return self(); }

    public Object[] getActions() { return actions; }
    public ScrapeParams setActions(Object[] actions) { this.actions = actions; return self(); }

    public Map<String, Object> getLocation() { return location; }
    public ScrapeParams setLocation(Map<String, Object> location) { this.location = location; return self(); }

    public Boolean getRemoveBase64Images() { return removeBase64Images; }
    public ScrapeParams setRemoveBase64Images(Boolean removeBase64Images) { this.removeBase64Images = removeBase64Images; return self(); }

    public Boolean getBlockAds() { return blockAds; }
    public ScrapeParams setBlockAds(Boolean blockAds) { this.blockAds = blockAds; return self(); }

    public String getProxy() { return proxy; }
    public ScrapeParams setProxy(String proxy) { this.proxy = proxy; return self(); }

    public Boolean getStoreInCache() { return storeInCache; }
    public ScrapeParams setStoreInCache(Boolean storeInCache) { this.storeInCache = storeInCache; return self(); }

    public Boolean getZeroDataRetention() { return zeroDataRetention; }
    public ScrapeParams setZeroDataRetention(Boolean zeroDataRetention) { this.zeroDataRetention = zeroDataRetention; return self(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ScrapeParams that = (ScrapeParams) o;
        return Arrays.equals(formats, that.formats) &&
                Arrays.equals(formatsAny, that.formatsAny) &&
                Objects.equals(headers, that.headers) &&
                Arrays.equals(includeTags, that.includeTags) &&
                Arrays.equals(excludeTags, that.excludeTags) &&
                Objects.equals(onlyMainContent, that.onlyMainContent) &&
                Objects.equals(waitFor, that.waitFor) &&
                Objects.equals(parsePDF, that.parsePDF) &&
                Arrays.equals(parsers, that.parsers) &&
                Objects.equals(timeout, that.timeout) &&
                Objects.equals(maxAge, that.maxAge) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(skipTlsVerification, that.skipTlsVerification) &&
                Arrays.equals(actions, that.actions) &&
                Objects.equals(location, that.location) &&
                Objects.equals(removeBase64Images, that.removeBase64Images) &&
                Objects.equals(blockAds, that.blockAds) &&
                Objects.equals(proxy, that.proxy) &&
                Objects.equals(storeInCache, that.storeInCache) &&
                Objects.equals(zeroDataRetention, that.zeroDataRetention);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), headers, onlyMainContent, waitFor, parsePDF, timeout,
                maxAge, mobile, skipTlsVerification, location, removeBase64Images, blockAds, proxy, storeInCache, zeroDataRetention);
        result = 31 * result + Arrays.hashCode(formats);
        result = 31 * result + Arrays.hashCode(formatsAny);
        result = 31 * result + Arrays.hashCode(includeTags);
        result = 31 * result + Arrays.hashCode(excludeTags);
        result = 31 * result + Arrays.hashCode(parsers);
        result = 31 * result + Arrays.hashCode(actions);
        return result;
    }

    @Override
    public String toString() {
        return "ScrapeParams{" +
                "formats=" + Arrays.toString(formats) +
                ", formatsAny=" + Arrays.toString(formatsAny) +
                ", headers=" + headers +
                ", includeTags=" + Arrays.toString(includeTags) +
                ", excludeTags=" + Arrays.toString(excludeTags) +
                ", onlyMainContent=" + onlyMainContent +
                ", waitFor=" + waitFor +
                ", parsePDF=" + parsePDF +
                ", parsers=" + Arrays.toString(parsers) +
                ", timeout=" + timeout +
                ", maxAge=" + maxAge +
                ", mobile=" + mobile +
                ", skipTlsVerification=" + skipTlsVerification +
                ", actions=" + Arrays.toString(actions) +
                ", location=" + location +
                ", removeBase64Images=" + removeBase64Images +
                ", blockAds=" + blockAds +
                ", proxy='" + proxy + '\'' +
                ", storeInCache=" + storeInCache +
                ", zeroDataRetention=" + zeroDataRetention +
                '}';
    }
}