package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Parameters for scrape requests.
 */
public class ScrapeParams extends BaseParams<ScrapeParams> {
    private String[] formats;
    private Map<String, String> headers;
    private String[] includeTags;
    private String[] excludeTags;
    private Boolean onlyMainContent;
    private Integer waitFor;
    private Boolean parsePDF;
    private Integer timeout;

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
     * Returns the timeout in seconds.
     *
     * @return the timeout in seconds
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Sets the timeout in seconds.
     *
     * @param timeout the timeout in seconds
     * @return this instance for method chaining
     */
    public ScrapeParams setTimeout(Integer timeout) {
        this.timeout = timeout;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ScrapeParams that = (ScrapeParams) o;
        return Arrays.equals(formats, that.formats) &&
                Objects.equals(headers, that.headers) &&
                Arrays.equals(includeTags, that.includeTags) &&
                Arrays.equals(excludeTags, that.excludeTags) &&
                Objects.equals(onlyMainContent, that.onlyMainContent) &&
                Objects.equals(waitFor, that.waitFor) &&
                Objects.equals(parsePDF, that.parsePDF) &&
                Objects.equals(timeout, that.timeout);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), headers, onlyMainContent, waitFor, parsePDF, timeout);
        result = 31 * result + Arrays.hashCode(formats);
        result = 31 * result + Arrays.hashCode(includeTags);
        result = 31 * result + Arrays.hashCode(excludeTags);
        return result;
    }

    @Override
    public String toString() {
        return "ScrapeParams{" +
                "formats=" + Arrays.toString(formats) +
                ", headers=" + headers +
                ", includeTags=" + Arrays.toString(includeTags) +
                ", excludeTags=" + Arrays.toString(excludeTags) +
                ", onlyMainContent=" + onlyMainContent +
                ", waitFor=" + waitFor +
                ", parsePDF=" + parsePDF +
                ", timeout=" + timeout +
                '}';
    }
}