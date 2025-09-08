package com.logger.logging;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String LOG_API_URL = "http://20.244.56.144/eva1uation-service/logs";
    private final String TOKEN = "sAWTuR";


    public void log(String stack, String level, String pkg, String message) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(TOKEN);

            Map<String, String> body = new HashMap<>();
            body.put("stack", stack);
            body.put("level", level);
            body.put("package", pkg);
            body.put("message", message);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
            restTemplate.postForEntity(LOG_API_URL, request, String.class);

        } catch (Exception e) {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }
}
