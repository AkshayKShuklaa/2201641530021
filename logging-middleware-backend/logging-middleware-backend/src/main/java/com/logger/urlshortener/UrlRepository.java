package com.logger.urlshortener;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlRepository {

    private Map<String, UrlMapping> storage = new ConcurrentHashMap<>();

    public void save(UrlMapping mapping) {
        storage.put(mapping.getShortcode(), mapping);
    }

    public UrlMapping findByShortcode(String shortcode) {
        return storage.get(shortcode);
    }

    public boolean exists(String shortcode) {
        return storage.containsKey(shortcode);
    }
}
