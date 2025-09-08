package com.logger.urlshortener;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlShortenerService {

    private final UrlRepository repository;
    private final Random random = new Random();
    private static final String ALPHANUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public UrlShortenerService(UrlRepository repository) {
        this.repository = repository;
    }

    // Generate a random shortcode
    private String generateShortcode(int length) {
        String shortcode;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
            }
            shortcode = sb.toString();
        } while (repository.exists(shortcode));
        return shortcode;
    }


    public UrlMapping createShortUrl(String originalUrl, Integer validityMinutes, String customShortcode) {
        String shortcode = (customShortcode != null && !customShortcode.isEmpty()) ? customShortcode : generateShortcode(6);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = now.plusMinutes((validityMinutes != null) ? validityMinutes : 30);

        UrlMapping mapping = new UrlMapping(shortcode, originalUrl, now, expiry);
        repository.save(mapping);
        return mapping;
    }


    public UrlMapping getUrlMapping(String shortcode) {
        return repository.findByShortcode(shortcode);
    }

    // Track clicks
    public void recordClick(String shortcode, String referrer, String geoLocation) {
        UrlMapping mapping = repository.findByShortcode(shortcode);
        if (mapping != null) {
            mapping.addClick(new ClickDetails(LocalDateTime.now(), referrer, geoLocation));
            repository.save(mapping);
        }
    }
}
