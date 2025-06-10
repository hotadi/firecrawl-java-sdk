package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a document retrieved from the Firecrawl API.
 */
public class FirecrawlDocument {
    private String markdown;
    private String html;
    private String rawHtml;
    private String screenshot;
    private String[] links;
    private Map<String, Object> metadata;

    /**
     * Returns the document content in Markdown format.
     *
     * @return the Markdown content
     */
    public String getMarkdown() {
        return markdown;
    }

    /**
     * Returns the document content in HTML format.
     *
     * @return the HTML content
     */
    public String getHtml() {
        return html;
    }

    /**
     * Returns the raw HTML content of the document.
     *
     * @return the raw HTML content
     */
    public String getRawHtml() {
        return rawHtml;
    }

    /**
     * Returns the screenshot of the document (base64 encoded).
     *
     * @return the screenshot
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Returns the links found in the document.
     *
     * @return the links
     */
    public String[] getLinks() {
        return links;
    }

    /**
     * Returns the metadata associated with the document.
     *
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * Returns the document content as text (alias for getMarkdown()).
     *
     * @return the text content
     */
    public String getText() {
        return markdown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirecrawlDocument that = (FirecrawlDocument) o;
        return Objects.equals(markdown, that.markdown) &&
                Objects.equals(html, that.html) &&
                Objects.equals(rawHtml, that.rawHtml) &&
                Objects.equals(screenshot, that.screenshot) &&
                Arrays.equals(links, that.links) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(markdown, html, rawHtml, screenshot, metadata);
        result = 31 * result + Arrays.hashCode(links);
        return result;
    }

    @Override
    public String toString() {
        return "FirecrawlDocument{" +
                "markdown='" + (markdown != null ? markdown.substring(0, Math.min(markdown.length(), 50)) + "..." : null) + '\'' +
                ", html='" + (html != null ? html.substring(0, Math.min(html.length(), 50)) + "..." : null) + '\'' +
                ", rawHtml='" + (rawHtml != null ? rawHtml.substring(0, Math.min(rawHtml.length(), 50)) + "..." : null) + '\'' +
                ", screenshot='" + (screenshot != null ? "[base64 data]" : null) + '\'' +
                ", links=" + Arrays.toString(links) +
                ", metadata=" + metadata +
                '}';
    }
}