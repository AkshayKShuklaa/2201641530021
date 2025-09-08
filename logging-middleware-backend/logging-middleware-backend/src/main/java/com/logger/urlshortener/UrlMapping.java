package com.logger.urlshortener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UrlMapping {
    private String shortcode;
    private String originalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiry;
    private int clickCount;
    private List<ClickDetails> clicks;

    public UrlMapping(String shortcode, String originalUrl, LocalDateTime createdAt, LocalDateTime expiry) {
        this.shortcode = shortcode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.expiry = expiry;
        this.clickCount = 0;
        this.clicks = new ArrayList<>();
    }

    // Getters and Setters
    public String getShortcode() { return shortcode; }
    public String getOriginalUrl() { return originalUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiry() { return expiry; }
    public int getClickCount() { return clickCount; }
    public List<ClickDetails> getClicks() { return clicks; }

    public void addClick(ClickDetails click) {
        this.clicks.add(click);
        this.clickCount++;
    }
}
