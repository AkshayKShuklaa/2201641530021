package com.logger.urlshortener;

import java.time.LocalDateTime;

public class ClickDetails {
    private LocalDateTime timestamp;
    private String referrer;
    private String geoLocation;

    public ClickDetails(LocalDateTime timestamp, String referrer, String geoLocation) {
        this.timestamp = timestamp;
        this.referrer = referrer;
        this.geoLocation = geoLocation;
    }

    // Getters
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getReferrer() { return referrer; }
    public String getGeoLocation() { return geoLocation; }
}
