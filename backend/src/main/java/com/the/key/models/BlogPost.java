package com.the.key.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPost {
    private Long id;
    private String date;
    private RenderedContent title;
    private RenderedContent content;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RenderedContent {
        private String rendered;

        @JsonProperty("rendered")
        public String getRendered() {
            return rendered;
        }

        @JsonProperty("rendered")
        public void setRendered(String rendered) {
            this.rendered = rendered;
        }
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("title")
    public RenderedContent getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(RenderedContent title) {
        this.title = title;
    }

    @JsonProperty("content")
    public RenderedContent getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(RenderedContent content) {
        this.content = content;
    }
}
