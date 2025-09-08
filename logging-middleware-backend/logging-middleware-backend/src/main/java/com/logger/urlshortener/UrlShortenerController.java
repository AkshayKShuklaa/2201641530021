package com.logger.urlshortener;

import com.logger.logging.LoggingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shorturls")
public class UrlShortenerController {

    private final UrlShortenerService service;
    private final LoggingService logger;

    public UrlShortenerController(UrlShortenerService service, LoggingService logger) {
        this.service = service;
        this.logger = logger;
    }

    // Create Short URL
    @PostMapping
    public ResponseEntity<Map<String, Object>> createShortUrl(@RequestBody Map<String, Object> body) {
        String url = (String) body.get("url");
        Integer validity = (body.get("validity") != null) ? (Integer) body.get("validity") : null;
        String shortcode = (String) body.get("shortcode");

        UrlMapping mapping = service.createShortUrl(url, validity, shortcode);

        Map<String, Object> response = new HashMap<>();
        response.put("shortLink", "http://localhost:8080/r/" + mapping.getShortcode());
        response.put("expiry", mapping.getExpiry().toString());

        // Logging
        logger.log("backend", "info", "controller", "Created short URL: " + mapping.getShortcode());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Retrieve Short URL statistics
    @GetMapping("/{shortcode}")
    public ResponseEntity<?> getStats(@PathVariable String shortcode) {
        UrlMapping mapping = service.getUrlMapping(shortcode);

        if (mapping == null) {
            logger.log("backend", "warn", "controller", "Stats request failed: shortcode not found " + shortcode);
            return new ResponseEntity<>("Shortcode not found", HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("originalUrl", mapping.getOriginalUrl());
        response.put("createdAt", mapping.getCreatedAt().toString());
        response.put("expiry", mapping.getExpiry().toString());
        response.put("clickCount", mapping.getClickCount());
        response.put("clicks", mapping.getClicks());

        logger.log("backend", "info", "controller", "Stats retrieved for shortcode: " + shortcode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Redirect to original URL
    @GetMapping("/r/{shortcode}")
    public ResponseEntity<?> redirect(@PathVariable String shortcode, @RequestHeader(value = "referer", required = false) String referer) {
        UrlMapping mapping = service.getUrlMapping(shortcode);

        if (mapping == null) {
            logger.log("backend", "warn", "controller", "Redirect failed: shortcode not found " + shortcode);
            return new ResponseEntity<>("Shortcode not found", HttpStatus.NOT_FOUND);
        }

        // Check expiry
        if (mapping.getExpiry().isBefore(java.time.LocalDateTime.now())) {
            logger.log("backend", "warn", "controller", "Redirect failed: shortcode expired " + shortcode);
            return new ResponseEntity<>("Link expired", HttpStatus.GONE);
        }

        // Record click
        service.recordClick(shortcode, referer, "unknown"); // Geo location placeholder
        logger.log("backend", "info", "controller", "Redirected shortcode: " + shortcode);

        return ResponseEntity.status(HttpStatus.FOUND).header("Location", mapping.getOriginalUrl()).build();
    }
}
