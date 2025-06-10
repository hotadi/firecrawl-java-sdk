package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * A single result item from a search response.
 */
public class SearchResult {
    private String title;
    private String description;
    private String url;
    private String markdown;
    private String html;
    private String rawHtml;
    private String[] links;
    private String screenshot;
    private Metadata metadata;

    /**
     * Returns the title of the search result.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the description of the search result.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the URL of the search result.
     *
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the content of the search result in Markdown format.
     *
     * @return the Markdown content
     */
    public String getMarkdown() {
        return markdown;
    }

    /**
     * Returns the content of the search result in HTML format.
     *
     * @return the HTML content
     */
    public String getHtml() {
        return html;
    }

    /**
     * Returns the raw HTML content of the search result.
     *
     * @return the raw HTML content
     */
    public String getRawHtml() {
        return rawHtml;
    }

    /**
     * Returns the links found in the search result.
     *
     * @return the links
     */
    public String[] getLinks() {
        return links;
    }

    /**
     * Returns the screenshot of the search result (base64 encoded).
     *
     * @return the screenshot
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Returns the metadata associated with the search result.
     *
     * @return the metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url) &&
                Objects.equals(markdown, that.markdown) &&
                Objects.equals(html, that.html) &&
                Objects.equals(rawHtml, that.rawHtml) &&
                Arrays.equals(links, that.links) &&
                Objects.equals(screenshot, that.screenshot) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, description, url, markdown, html, rawHtml, screenshot, metadata);
        result = 31 * result + Arrays.hashCode(links);
        return result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", markdown='" + (markdown != null ? markdown.substring(0, Math.min(markdown.length(), 50)) + "..." : null) + '\'' +
                ", html='" + (html != null ? html.substring(0, Math.min(html.length(), 50)) + "..." : null) + '\'' +
                ", rawHtml='" + (rawHtml != null ? rawHtml.substring(0, Math.min(rawHtml.length(), 50)) + "..." : null) + '\'' +
                ", links=" + Arrays.toString(links) +
                ", screenshot='" + (screenshot != null ? "[base64 data]" : null) + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    /**
     * Metadata for a search result.
     */
    public static class Metadata {
        private String title;
        private String description;
        private String sourceURL;
        private int statusCode;
        private String error;

        /**
         * Returns the title from the metadata.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Returns the description from the metadata.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns the source URL from the metadata.
         *
         * @return the source URL
         */
        public String getSourceURL() {
            return sourceURL;
        }

        /**
         * Returns the HTTP status code from the metadata.
         *
         * @return the HTTP status code
         */
        public int getStatusCode() {
            return statusCode;
        }

        /**
         * Returns the error message from the metadata.
         *
         * @return the error message
         */
        public String getError() {
            return error;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Metadata metadata = (Metadata) o;
            return statusCode == metadata.statusCode &&
                    Objects.equals(title, metadata.title) &&
                    Objects.equals(description, metadata.description) &&
                    Objects.equals(sourceURL, metadata.sourceURL) &&
                    Objects.equals(error, metadata.error);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, sourceURL, statusCode, error);
        }

        @Override
        public String toString() {
            return "Metadata{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", sourceURL='" + sourceURL + '\'' +
                    ", statusCode=" + statusCode +
                    ", error='" + error + '\'' +
                    '}';
        }
    }
}